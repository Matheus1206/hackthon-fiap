package com.fiap.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hackathon.JwtTokenUtil;
import com.fiap.hackathon.Utils;
import com.fiap.hackathon.dto.ClienteRequest;
import com.fiap.hackathon.model.Usuario;
import com.fiap.hackathon.repository.UsuarioRepository;
import com.fiap.hackathon.service.ClienteService;
import com.fiap.hackathon.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void clienteControllerFromService() throws Exception {
        String token = JwtTokenUtil.generateToken("matheus");
        when(tokenService.gerarToken(any())).thenReturn(token);
        tokenService.gerarToken(new Usuario());
        ClienteRequest clienteRequest = new ClienteRequest(Utils.generateCPF(), "Teste nome", "teste@teste.com", "123456789", "Rua Teste", "Teste Cidade", "Teste Estado", "0000001", "Teste Pais");
        String req = mapper.writeValueAsString(clienteRequest);
        when(clienteService.criar(any())).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(post("/api/cliente")
                        .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}