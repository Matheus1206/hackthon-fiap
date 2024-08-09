package com.fiap.hackathon.config;

import com.fiap.hackathon.model.Usuario;
import com.fiap.hackathon.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarregaBanco {

        @Bean
        CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
            return args -> {
                Usuario usuario = new Usuario(1l, "teste", "$2a$10$C6YDThX15fvGefbOFL2pR.90rgWABpUGwFFBgFFkTOmpIoKXGf69K");
                usuarioRepository.save(usuario);
            };
        }
    }