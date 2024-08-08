package com.fiap.hackathon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cartao {

    private String cpf;

    private Double limite;

    @Id
    private String numero;

    private String dataValidade;

    private String cvv;

    public void debitar(Double limite) {
        this.limite -= limite;
    }
}
