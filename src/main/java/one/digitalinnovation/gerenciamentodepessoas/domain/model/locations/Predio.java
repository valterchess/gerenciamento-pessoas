package one.digitalinnovation.gerenciamentodepessoas.domain.model.locations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors.Funcionario;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_predio")
public class Predio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnoreProperties("predio")
    Conjunto conjunto;

    //adicionar categoria

    @OneToMany(mappedBy = "predio",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("predio")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "predio",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("predio")
    private List<Funcionario> funcionarios;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
