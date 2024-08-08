package com.fiap.hackathon.service;

import com.fiap.hackathon.model.Usuario;
import com.fiap.hackathon.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Test
    void loadUserByUsername() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByLogin(any())).thenReturn(usuario);
        UserDetails teste = autenticacaoService.loadUserByUsername("teste");
        assertNotNull(teste);
    }
}