package one.digitalinnovation.gerenciamentodepessoas.domain.repository.contributors;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    Optional<Funcionario> findByCredencial(String credencial);
}
