package br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //alterar o pattern date time format
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime entrada;

    @ManyToOne
    @JsonIgnoreProperties("entrada")
    private Funcionario funcionario;

    public Entrada(LocalDateTime entrada, Funcionario funcionario) {
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

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
