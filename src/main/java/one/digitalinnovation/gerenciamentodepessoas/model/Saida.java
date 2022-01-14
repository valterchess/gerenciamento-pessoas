package one.digitalinnovation.gerenciamentodepessoas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_saida")
public class Saida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate saida;

    @ManyToOne
    @JsonIgnoreProperties("saida")
    private Funcionario funcionario;

    public Saida(LocalDate saida, Funcionario funcionario) {
        this.saida = saida;
        this.funcionario = funcionario;
    }

    public Saida() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getSaida() {
        return saida;
    }

    public void setSaida(LocalDate saida) {
        this.saida = saida;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}