package com.fiap.hackathon.service;

import com.fiap.hackathon.dto.ClienteRequest;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> criar(ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel();

        clienteRepository.save(cliente);

        HashMap<String, Object> object = new HashMap<>();

        object.put("id_cliente", cliente.getId());

        return ResponseEntity.status(200).body(object);
    }
}
