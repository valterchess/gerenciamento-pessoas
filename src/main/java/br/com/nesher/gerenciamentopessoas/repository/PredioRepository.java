package br.com.nesher.gerenciamentopessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nesher.gerenciamentopessoas.domain.entity.Predio;

@Repository
public interface PredioRepository extends JpaRepository<Predio, Long> {
}
