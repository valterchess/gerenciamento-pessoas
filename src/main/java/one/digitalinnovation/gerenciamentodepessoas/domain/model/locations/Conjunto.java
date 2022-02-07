package one.digitalinnovation.gerenciamentodepessoas.domain.model.locations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors.Gerente;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_conjunto")
public class Conjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 255)
    private String endereco;

    @OneToMany(mappedBy = "conjunto", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("conjunto")
    private List<Predio> predio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Predio> getPredio() {
        return predio;
    }

    public void setPredio(List<Predio> predio) {
        this.predio = predio;
    }
}
