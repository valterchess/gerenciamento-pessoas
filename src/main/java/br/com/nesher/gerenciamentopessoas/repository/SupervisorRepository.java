package br.com.nesher.gerenciamentopessoas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nesher.gerenciamentopessoas.domain.entity.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    Optional<Supervisor> findByEmail(String email);
}
