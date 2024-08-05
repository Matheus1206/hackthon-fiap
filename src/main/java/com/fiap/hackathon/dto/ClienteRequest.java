package com.fiap.hackathon.dto;

import com.fiap.hackathon.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(@NotBlank @CPF String cpf, @NotBlank String nome, @NotBlank @Email String email,
                             @NotBlank String telefone, @NotBlank String rua, @NotBlank String cidade, @NotBlank String estado,
                             @NotBlank String cep, @NotBlank String pais) {

    public Cliente toModel() {
        return new Cliente(cpf, nome, email, telefone, rua, cidade, estado, cep, pais);
    }
}
