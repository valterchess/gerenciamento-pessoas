package one.digitalinnovation.gerenciamentodepessoas.domain.repository;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {
}
