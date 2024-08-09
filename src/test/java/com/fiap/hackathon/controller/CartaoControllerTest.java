package com.fiap.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hackathon.model.Cartao;
import com.fiap.hackathon.repository.UsuarioRepository;
import com.fiap.hackathon.service.CartaoService;
import com.fiap.hackathon.service.ClienteService;
import com.fiap.hackathon.service.TokenService;
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

@WebMvcTest(CartaoController.class)
class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartaoService service;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private ClienteService clienteService;


    @Test
    void cartaoControllerFromService() throws Exception {
        when(service.validarCriacao(any())).thenReturn(ResponseEntity.ok().build());
        Cartao cartao = new Cartao();
        cartao.setCpf("12345677777");
        cartao.setNumero("12345");
        cartao.setDataValidade("12345");
        cartao.setLimite(1000000.0);
        cartao.setCvv("123");
        String req = mapper.writeValueAsString(cartao);
        this.mockMvc.perform(post("/api/cartao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}