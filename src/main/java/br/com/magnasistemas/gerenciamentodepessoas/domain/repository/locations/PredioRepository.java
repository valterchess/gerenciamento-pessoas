package br.com.magnasistemas.gerenciamentodepessoas.domain.repository.locations;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Predio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredioRepository extends JpaRepository<Predio, Long> {
}
