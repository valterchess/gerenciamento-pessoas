package one.digitalinnovation.gerenciamentodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gerenciamentodepessoas.model.CadastroFuncionario;

@Repository
public interface CadastroFuncionarioRepository extends JpaRepository<CadastroFuncionario, Long>{

}
