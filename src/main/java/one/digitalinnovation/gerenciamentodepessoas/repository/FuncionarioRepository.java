package one.digitalinnovation.gerenciamentodepessoas.repository;

import one.digitalinnovation.gerenciamentodepessoas.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    Optional<Funcionario> findByCredencial(String credencial);
}
