package one.digitalinnovation.gerenciamentodepessoas.domain.repository.locations;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.locations.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);
    public List<Endereco> findAllByUf(String uf);
    public List<Endereco> findAllByDdd(String ddd);
    public List<Endereco> findAllByLocalidade(String localidade);
}
