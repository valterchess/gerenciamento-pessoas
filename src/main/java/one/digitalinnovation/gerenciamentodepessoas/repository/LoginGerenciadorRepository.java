package one.digitalinnovation.gerenciamentodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gerenciamentodepessoas.model.LoginGerenciador;

@Repository
public interface LoginGerenciadorRepository extends JpaRepository<LoginGerenciador, Long> {

}
