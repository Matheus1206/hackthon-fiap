package com.fiap.hackathon.service;

import com.fiap.hackathon.dto.ClienteRequest;
import com.fiap.hackathon.model.Cliente;
import com.fiap.hackathon.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void criarUmClienteComSucesso() {
        ClienteRequest request = new ClienteRequest("123456666", "Teste Nome", "teste@teste.com", "12345669", "Rua Teste", "Cidade Teste", "Estado Teste", "00000000", "Pais Teste");
        Cliente cliente = request.toModel();
        cliente.setId(1);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<?> entity = clienteService.criar(request);
        assertNotNull(entity);
        assertTrue(entity.getStatusCode().is2xxSuccessful());
    }
}