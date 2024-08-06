package com.fiap.hackathon.service;

import com.fiap.hackathon.model.Cartao;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.repository.CartaoRepository;
import com.fiap.hackathon.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> validarCriacao(Cartao cartao) {
        List<Optional<Cartao>> optionalCartoes = cartaoRepository.findByCpf(cartao.getCpf());

        Optional<Cliente> cliente = clienteRepository.findByCpf(cartao.getCpf());

        if (cliente.isEmpty()) {
            return ResponseEntity.status(404).body("Cliente não encontrado");
        }
        System.out.println(optionalCartoes.size());
        if (optionalCartoes.size() >= 2) {
            return ResponseEntity.status(403).body("Limite de cartões atingido");
        }

        cartaoRepository.save(cartao);

        return ResponseEntity.status(200).build();
    }
}
