package com.fiap.hackathon.controller;

import com.fiap.hackathon.dto.PagamentoRequest;
import com.fiap.hackathon.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<?> gerarPagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest) {
        return pagamentoService.pagar(pagamentoRequest);
    }

    @GetMapping("cliente/{cpf}")
    public ResponseEntity<?> consultarPagamento(@PathVariable String cpf) {
        return pagamentoService.visualizar(cpf);
    }
}
