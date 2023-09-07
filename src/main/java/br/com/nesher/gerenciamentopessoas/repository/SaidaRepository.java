package br.com.nesher.gerenciamentopessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nesher.gerenciamentopessoas.domain.entity.Saida;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {
}
