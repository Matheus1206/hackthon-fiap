package com.fiap.hackathon.service;

import com.fiap.hackathon.model.Cartao;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.repository.CartaoRepository;
import com.fiap.hackathon.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @Test
    void validarCriacaoDoCartaoComSucesso() {
        Cartao cartao = new Cartao();
        cartao.setCpf("123456789");
        List<Optional<Cartao>> optionalCartoes = new ArrayList<>();
        Cliente cliente = new Cliente(cartao.getCpf(), "Teste", "teste@teste.com", "12345666", "Rua teste", "Teste Cidade", "Teste Estado", "222222222", "Teste Pais");
        cliente.setCartoes(cartao);
        when(clienteRepository.findByCpf(cartao.getCpf())).thenReturn(Optional.of(cliente));
        when(cartaoRepository.findByCpf(cartao.getCpf())).thenReturn(optionalCartoes);
        ResponseEntity<?> responseEntity = cartaoService.validarCriacao(cartao);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

    }
    @Test
    void validarQueOClienteEstaVazio(){
        Cartao cartao = new Cartao();
        cartao.setCpf("123456789");
        List<Optional<Cartao>> optionalCartoes = new ArrayList<>();
        Cliente cliente = null;
        when(cartaoRepository.findByCpf(cartao.getCpf())).thenReturn(optionalCartoes);
        when(clienteRepository.findByCpf(cartao.getCpf())).thenReturn(Optional.ofNullable(cliente));
        ResponseEntity<?> responseEntity = cartaoService.validarCriacao(cartao);
        assertTrue(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));
        assertEquals("Cliente não encontrado", responseEntity.getBody());
    }

    @Test
    void validarQueOLimiteDoCartaoFoiAtingido(){
        Cartao cartao = new Cartao();
        cartao.setCpf("123456789");
        List<Optional<Cartao>> optionalCartoes = new ArrayList<>();
        optionalCartoes.add(Optional.of(new Cartao()));
        optionalCartoes.add(Optional.of(new Cartao()));
        Cliente cliente = new Cliente(cartao.getCpf(), "Teste", "teste@teste.com", "12345666", "Rua teste", "Teste Cidade", "Teste Estado", "222222222", "Teste Pais");
        when(cartaoRepository.findByCpf(cartao.getCpf())).thenReturn(optionalCartoes);
        when(clienteRepository.findByCpf(cartao.getCpf())).thenReturn(Optional.of(cliente));
        ResponseEntity<?> responseEntity = cartaoService.validarCriacao(cartao);
        assertTrue(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.FORBIDDEN));
        assertEquals("Limite de cartões atingido", responseEntity.getBody());
    }

}
