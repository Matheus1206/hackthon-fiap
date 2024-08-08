package com.fiap.hackathon.dto;

import com.fiap.hackathon.model.Pagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PagamentoRequest(@NotBlank @CPF String cpf, @NotNull String numero, @NotBlank String dataValidade,
                               @NotBlank String cvv, @NotNull Double valor) {

    public Pagamento toModel() {
        return new Pagamento(cpf, numero, dataValidade, cvv, valor);
    }
}
