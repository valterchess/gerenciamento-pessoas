package one.digitalinnovation.gerenciamentodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gerenciamentodepessoas.model.CadastroGerenciador;

import java.util.Optional;

@Repository
public interface CadastroGerenciadorRepository extends JpaRepository<CadastroGerenciador, Long> {
    Optional<CadastroGerenciador> findByUsuario(String userName);

}
