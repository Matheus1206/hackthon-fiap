package com.fiap.hackathon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String cpf;

    private String numero;

    private String dataValidade;

    private String cvv;

    private Double valor;

    public Pagamento(String cpf, String numero, String dataValidade, String cvv, Double valor) {
        this.cpf = cpf;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
        this.valor = valor;
    }
}
