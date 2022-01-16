package one.digitalinnovation.gerenciamentodepessoas.repository;

import one.digitalinnovation.gerenciamentodepessoas.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
