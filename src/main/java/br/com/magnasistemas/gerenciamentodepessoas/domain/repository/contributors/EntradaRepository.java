package br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
