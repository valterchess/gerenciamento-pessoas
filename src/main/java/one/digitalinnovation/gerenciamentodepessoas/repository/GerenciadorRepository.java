package one.digitalinnovation.gerenciamentodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gerenciamentodepessoas.model.Gerenciador;

import java.util.Optional;

@Repository
public interface GerenciadorRepository extends JpaRepository<Gerenciador, Long> {
    Optional<Gerenciador> findByUsuario(String userName);

}
