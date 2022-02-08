package one.digitalinnovation.gerenciamentodepessoas.domain.model.locations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_conjunto")
public class Conjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "conjunto", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("conjunto")
    private List<Predio> predio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Predio> getPredio() {
        return predio;
    }

    public void setPredio(List<Predio> predio) {
        this.predio = predio;
    }
}
