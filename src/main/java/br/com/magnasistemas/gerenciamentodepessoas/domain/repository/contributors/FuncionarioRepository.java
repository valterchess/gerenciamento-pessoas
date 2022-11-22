package br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    Optional<Funcionario> findByCredencial(String credencial);
}
