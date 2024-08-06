package com.fiap.hackathon.controller;

import com.fiap.hackathon.model.Cartao;
import com.fiap.hackathon.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<?> criarCartao(@Valid @RequestBody Cartao cartao) {
        return cartaoService.validarCriacao(cartao);
    }
}
