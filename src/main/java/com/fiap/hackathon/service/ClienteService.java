package com.fiap.hackathon.service;

import com.fiap.hackathon.dto.ClienteRequest;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criar(ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel();

        clienteRepository.save(cliente);

        return cliente;
    }
}
