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

    private Integer limite;

    @Id
    private Long numero;

    private String dataValidade;

    private String cvv;

}
