package com.fiap.hackathon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    @CPF
    private String cpf;

    private String nome;

    @Email
    private String email;

    private String telefone;

    private String rua;

    private String cidade;

    private String estado;

    private String cep;

    private String pais;

    public Cliente(String cpf, String nome, String email, String telefone, String rua, String cidade, String estado, String cep, String pais) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pais = pais;
    }
}
