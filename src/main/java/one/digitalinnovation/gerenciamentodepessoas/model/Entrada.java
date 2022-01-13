package one.digitalinnovation.gerenciamentodepessoas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate entrada;

    @ManyToOne
    @JsonIgnoreProperties("entrada")
    private Funcionario funcionario;

    public Entrada(LocalDate entrada, Funcionario funcionario) {
        this.entrada = entrada;
        this.funcionario = funcionario;
    }

    public Entrada() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDate entrada) {
        this.entrada = entrada;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
