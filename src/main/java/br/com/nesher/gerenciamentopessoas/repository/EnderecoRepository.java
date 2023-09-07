package br.com.nesher.gerenciamentopessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nesher.gerenciamentopessoas.domain.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);
    public List<Endereco> findAllByUf(String uf);
    public List<Endereco> findAllByDdd(String ddd);
    public List<Endereco> findAllByLocalidade(String localidade);
}
