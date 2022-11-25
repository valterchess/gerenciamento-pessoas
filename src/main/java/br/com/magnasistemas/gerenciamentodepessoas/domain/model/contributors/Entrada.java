package br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_entrada")
public class Entrada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// alterar o pattern date time format
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private LocalDateTime entrada;

	@ManyToOne
	@JsonIgnoreProperties("entrada")
	private Funcionario funcionario;

	public long getId() {
		return id;
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
