package com.fiap.hackathon.controller;

import com.fiap.hackathon.dto.ClienteRequest;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteService.criar(clienteRequest);

        HashMap<String, Object> object = new HashMap<>();

        object.put("id_cliente", cliente.getId());

        return ResponseEntity.status(200).body(object);
    }
}
