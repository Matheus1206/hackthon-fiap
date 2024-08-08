package com.fiap.hackathon.service;

import com.fiap.hackathon.dto.PagamentoRequest;
import com.fiap.hackathon.model.Cartao;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.model.Pagamento;
import com.fiap.hackathon.repository.CartaoRepository;
import com.fiap.hackathon.repository.ClienteRepository;
import com.fiap.hackathon.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void validarQueOPagamentoFoiRealizadoComSucesso() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest("123456", "123456", "123456", "123", 1000.0);
        Pagamento pagamento = pagamentoRequest.toModel();
        Cliente cliente = new Cliente();
        Cartao cartao = new Cartao();
        cartao.setDataValidade("123456");
        cartao.setCvv("123");
        cartao.setLimite(20000000.0);
        cartao.setNumero("123456");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(pagamentoRequest.cpf())).thenReturn(Optional.of(cliente));
        ResponseEntity<?> entity = pagamentoService.pagar(pagamentoRequest);
        assertTrue(entity.getStatusCode().is2xxSuccessful());
    }
    @Test
    void validarQueADataDeValidadeFoiRealizadoInformadaIncorretamente() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest("123456", "123456", "123456", "123", 1000.0);
        Pagamento pagamento = pagamentoRequest.toModel();
        Cliente cliente = new Cliente();
        Cartao cartao = new Cartao();
        cartao.setDataValidade("123453");
        cartao.setCvv("123");
        cartao.setLimite(20000000.0);
        cartao.setNumero("123456");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(pagamentoRequest.cpf())).thenReturn(Optional.of(cliente));
        ResponseEntity<?> entity = pagamentoService.pagar(pagamentoRequest);
        assertTrue(entity.getStatusCode().is4xxClientError());
        assertEquals("Data de validade do cartão incorreta", entity.getBody());

    }
    @Test
    void validarQueONumeroDoCVVFoiRealizadoInformadoIncorretamente() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest("123456", "123456", "123456", "123", 1000.0);
        Pagamento pagamento = pagamentoRequest.toModel();
        Cliente cliente = new Cliente();
        Cartao cartao = new Cartao();
        cartao.setDataValidade("123456");
        cartao.setCvv("123333");
        cartao.setLimite(20000000.0);
        cartao.setNumero("123456");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(pagamentoRequest.cpf())).thenReturn(Optional.of(cliente));
        ResponseEntity<?> entity = pagamentoService.pagar(pagamentoRequest);
        assertTrue(entity.getStatusCode().is4xxClientError());
        assertEquals("Número cvv do cartão incorreto", entity.getBody());

    }

    @Test
    void validarQueONumeroDoCartaoFoiRealizadoInformadoIncorretamente() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest("123456", "1234567", "123456", "123", 1000.0);
        Pagamento pagamento = pagamentoRequest.toModel();
        Cliente cliente = new Cliente();
        Cartao cartao = new Cartao();
        cartao.setDataValidade("123456");
        cartao.setCvv("123");
        cartao.setLimite(20000000.0);
        cartao.setNumero("123456");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(pagamentoRequest.cpf())).thenReturn(Optional.of(cliente));
        ResponseEntity<?> entity = pagamentoService.pagar(pagamentoRequest);
        assertTrue(entity.getStatusCode().is4xxClientError());
        assertEquals("Número de cartão incorreto", entity.getBody());

    }

    @Test
    void validarQueOCartaoNaoTemLimite() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest("123456", "123456", "123456", "123", 1000.0);
        Pagamento pagamento = pagamentoRequest.toModel();
        Cliente cliente = new Cliente();
        Cartao cartao = new Cartao();
        cartao.setDataValidade("123456");
        cartao.setCvv("123");
        cartao.setLimite(2.0);
        cartao.setNumero("123456");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(pagamentoRequest.cpf())).thenReturn(Optional.of(cliente));
        ResponseEntity<?> entity = pagamentoService.pagar(pagamentoRequest);
        assertTrue(entity.getStatusCode().is4xxClientError());
        assertEquals("Limite do cartão estourado", entity.getBody());

    }

    @Test
    void validarQueOClienteNãoFoiEncontrado() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest("123456", "123456", "123456", "123", 1000.0);
        Pagamento pagamento = pagamentoRequest.toModel();
        Cliente cliente = new Cliente();
        Cartao cartao = new Cartao();
        cartao.setDataValidade("123456");
        cartao.setCvv("123");
        cartao.setLimite(20000000.0);
        cartao.setNumero("123456");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(pagamentoRequest.cpf())).thenReturn(Optional.empty());
        ResponseEntity<?> entity = pagamentoService.pagar(pagamentoRequest);
        assertTrue(entity.getStatusCode().is4xxClientError());
        assertEquals("Cliente não encontrado", entity.getBody());
    }

    @Test
    void visualizar() {
        String cpf = "123456799";
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(10000.0);
        List<Optional<Pagamento>> list = new ArrayList<>();
        list.add(Optional.of(pagamento));
        when(pagamentoRepository.findByCpf(cpf)).thenReturn(list);
        ResponseEntity<?> entity = pagamentoService.visualizar(cpf);
        assertTrue(entity.getStatusCode().is2xxSuccessful());
        assertEquals("[{metodo_pagamento=cartão de crédito, valor=10000.0, descricao=Compra de produto X, status=aprovado}]", entity.getBody().toString());

    }

    @Test
    void visualizarQueNaoPossuiNenhumResgistroDePagamento() {
        String cpf = "123456799";
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(10000.0);
        List<Optional<Pagamento>> list = new ArrayList<>();
        when(pagamentoRepository.findByCpf(cpf)).thenReturn(list);
        ResponseEntity<?> entity = pagamentoService.visualizar(cpf);
        assertTrue(entity.getStatusCode().is4xxClientError());
        assertEquals("Nenhum registro encontrado", entity.getBody());

    }
}