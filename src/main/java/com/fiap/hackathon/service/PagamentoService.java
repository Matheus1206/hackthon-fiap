package com.fiap.hackathon.service;

import com.fiap.hackathon.dto.PagamentoRequest;
import com.fiap.hackathon.model.Cartao;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.model.Pagamento;
import com.fiap.hackathon.repository.CartaoRepository;
import com.fiap.hackathon.repository.ClienteRepository;
import com.fiap.hackathon.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public ResponseEntity<?> pagar(PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = pagamentoRequest.toModel();

        Optional<Cliente> optionalCliente = clienteRepository.findByCpf(pagamentoRequest.cpf());

        if (optionalCliente.isEmpty()) {
            return ResponseEntity.status(404).body("Cliente não encontrado");
        }

        for (Cartao c : optionalCliente.get().getCartoes()) {
            if (pagamentoRequest.numero().equals(c.getNumero())) {
                if (!Objects.equals(pagamentoRequest.dataValidade(), c.getDataValidade())) {
                    return ResponseEntity.status(404).body("Data de validade do cartão incorreta");
                }
                if (!Objects.equals(pagamentoRequest.cvv(), c.getCvv())) {
                    return ResponseEntity.status(404).body("Número cvv do cartão incorreto");
                }

                boolean temLimite = (c.getLimite() - pagamentoRequest.valor()) >= 0.0;

                if (!temLimite) {
                    return ResponseEntity.status(402).body("Limite do cartão estourado");
                }

                c.debitar(pagamentoRequest.valor());

                cartaoRepository.save(c);

                pagamentoRepository.save(pagamento);

                HashMap<String, Object> object = new HashMap<>();

                object.put("chave_pagamento", pagamento.getId());

                return ResponseEntity.status(200).body(object);
            }
        }

        return ResponseEntity.status(404).body("Número de cartão incorreto");
    }

    public ResponseEntity<?> visualizar(String cpf) {

        List<Optional<Pagamento>> optionalPagamento = pagamentoRepository.findByCpf(cpf);

        if (optionalPagamento.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum registro encontrado");
        }

        List<HashMap<String, Object>> resultados = new ArrayList<>();

        for (Optional<Pagamento> p : optionalPagamento) {
            HashMap<String, Object> object = new HashMap<>();

            object.put("valor", p.get().getValor());
            object.put("descricao", "Compra de produto X");
            object.put("metodo_pagamento", "cartão de crédito");
            object.put("status", "aprovado");

            resultados.add(object);
        }



        return ResponseEntity.status(200).body(resultados);

    }
}
