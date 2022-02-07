package one.digitalinnovation.gerenciamentodepessoas.domain.repository.contributors;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
