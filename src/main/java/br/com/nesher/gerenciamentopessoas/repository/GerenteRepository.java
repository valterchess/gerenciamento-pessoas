package br.com.nesher.gerenciamentopessoas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nesher.gerenciamentopessoas.domain.entity.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findByEmail(String email);
}
