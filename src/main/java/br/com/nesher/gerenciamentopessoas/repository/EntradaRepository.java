package br.com.nesher.gerenciamentopessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nesher.gerenciamentopessoas.domain.entity.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
