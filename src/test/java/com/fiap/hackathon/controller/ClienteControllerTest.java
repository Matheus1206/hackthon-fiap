package com.fiap.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hackathon.Utils;
import com.fiap.hackathon.dto.ClienteRequest;
import com.fiap.hackathon.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void clienteControllerFromService() throws Exception {
        ClienteRequest clienteRequest = new ClienteRequest(Utils.generateCPF(), "Teste nome", "teste@teste.com", "123456789", "Rua Teste", "Teste Cidade", "Teste Estado", "0000001", "Teste Pais");
        String req = mapper.writeValueAsString(clienteRequest);
        when(clienteService.criar(any())).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(post("/api/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }
}