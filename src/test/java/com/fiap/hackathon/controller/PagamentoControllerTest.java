package com.fiap.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hackathon.Utils;
import com.fiap.hackathon.dto.PagamentoRequest;
import com.fiap.hackathon.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagamentoController.class)
class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoService pagamentoService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void pagamentoControllerFromService() throws Exception {
        PagamentoRequest pagamentoRequest = new PagamentoRequest(Utils.generateCPF(), "1234", "12345", "123", 122222.0);
        String req = mapper.writeValueAsString(pagamentoRequest);
        when(pagamentoService.pagar(any())).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(post("/api/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void pagamentoGetControllerFromService() throws Exception {
        String cpf = Utils.generateCPF();
        when(pagamentoService.visualizar(cpf)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(get("/api/pagamento/cliente/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}