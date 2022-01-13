package one.digitalinnovation.gerenciamentodepessoas.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	private String nome;

	@NotBlank(message = "O atributo número pessoal é obrigatório!")
	private String numeroPessoal;

	@CPF
	private long cpf;

	@NotBlank(message = "O atributo equipe é obrigatório!")
	private String equipe;

	@OneToMany(mappedBy = "funcionario",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private List<Entrada> entrada;

	@OneToMany(mappedBy = "funcionario",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private List<Saida> saida;
	
	@ManyToOne
	@JsonIgnoreProperties("funcionario")
	private Gerenciador gerenciador;

	//manter apenas em caso de aplicação do Junit
	public Funcionario(long id, String nome, String numeroPessoal, long cpf, String equipe, List<Entrada> entrada, List<Saida> saida) {
		this.id = id;
		this.nome = nome;
		this.numeroPessoal = numeroPessoal;
		this.cpf = cpf;
		this.equipe = equipe;
		this.entrada = entrada;
		this.saida = saida;
	}

	//manter apenas em caso de aplicação do Junit
	public Funcionario() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroPessoal() {
		return numeroPessoal;
	}

	public void setNumeroPessoal(String numeroPessoal) {
		this.numeroPessoal = numeroPessoal;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public List<Entrada> getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada.add(entrada);
	}

	public List<Saida> getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida.add(saida);
	}

	public Gerenciador getGerenciador() {
		return gerenciador;
	}

	public void setGerenciador(Gerenciador gerenciador) {
		this.gerenciador = gerenciador;
	}
}