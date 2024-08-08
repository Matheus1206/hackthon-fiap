package com.fiap.hackathon.service;

import com.fiap.hackathon.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Test
    void gerarToken() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> tokenService.gerarToken(new Usuario()));
        assertEquals("The Secret cannot be null", exception.getMessage());
    }

    @Test
    void getSubject() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> tokenService.getSubject(""));
        assertEquals("The Secret cannot be null", exception.getMessage());
    }
}