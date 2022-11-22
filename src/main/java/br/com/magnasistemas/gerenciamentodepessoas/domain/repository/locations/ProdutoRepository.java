package br.com.magnasistemas.gerenciamentodepessoas.domain.repository.locations;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
