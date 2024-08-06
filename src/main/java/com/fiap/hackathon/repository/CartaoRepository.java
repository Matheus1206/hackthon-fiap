package com.fiap.hackathon.repository;

import com.fiap.hackathon.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    List<Optional<Cartao>> findByCpf(String cpf);
}
