package one.digitalinnovation.gerenciamentodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gerenciamentodepessoas.model.CadastroFuncionario;

import java.util.Optional;

@Repository
public interface CadastroFuncionarioRepository extends JpaRepository<CadastroFuncionario, Long>{
    Optional<CadastroFuncionario> findByCpf(long cpf);
}
