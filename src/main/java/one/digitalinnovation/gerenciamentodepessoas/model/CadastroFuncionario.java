package one.digitalinnovation.gerenciamentodepessoas.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_cadastroFuncionario")
public class CadastroFuncionario {
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date entrada;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date saida;
	
	@ManyToOne
	@JsonIgnoreProperties("cadastroFuncionario")
	private CadastroGerenciador cadastroGerenciador;
	
	public CadastroFuncionario(long id, String nome, String numeroPessoal, long cpf, String equipe, Date entrada, Date saida) {
		this.id = id;
		this.nome = nome;
		this.numeroPessoal = numeroPessoal;
		this.cpf = cpf;
		this.equipe = equipe;
		this.entrada = entrada;
		this.saida = saida;
	}

	public CadastroFuncionario() {
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


	public CadastroGerenciador getCadastrogerenciador() {
		return cadastroGerenciador;
	}


	public void setCadastrogerenciador(CadastroGerenciador cadastrogerenciador) {
		this.cadastroGerenciador = cadastrogerenciador;
	}
	
	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

}
