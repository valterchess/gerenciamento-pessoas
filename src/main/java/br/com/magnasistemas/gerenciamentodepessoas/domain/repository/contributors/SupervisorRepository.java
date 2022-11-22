package br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    Optional<Supervisor> findByEmail(String email);
}
