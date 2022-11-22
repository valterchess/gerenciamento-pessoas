package br.com.magnasistemas.gerenciamentodepessoas.domain.repository.locations;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuntoRepository extends JpaRepository<Conjunto, Long> {
}
