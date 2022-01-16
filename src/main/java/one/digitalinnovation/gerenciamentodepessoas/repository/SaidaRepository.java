package one.digitalinnovation.gerenciamentodepessoas.repository;

import one.digitalinnovation.gerenciamentodepessoas.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {
}
