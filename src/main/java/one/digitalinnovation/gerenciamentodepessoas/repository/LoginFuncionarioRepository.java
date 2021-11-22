package one.digitalinnovation.gerenciamentodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gerenciamentodepessoas.model.LoginFuncionario;

@Repository
public interface LoginFuncionarioRepository extends JpaRepository<LoginFuncionario, Long> {

}
