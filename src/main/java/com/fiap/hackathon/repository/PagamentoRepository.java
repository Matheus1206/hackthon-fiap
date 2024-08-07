package com.fiap.hackathon.repository;

import com.fiap.hackathon.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    List<Optional<Pagamento>> findByCpf(String cpf);
}
