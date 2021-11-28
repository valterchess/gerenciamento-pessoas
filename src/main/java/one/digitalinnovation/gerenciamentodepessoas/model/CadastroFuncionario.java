package one.digitalinnovation.gerenciamentodepessoas.model;

import java.time.LocalDate;
import java.util.List;

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
	private List<LocalDate> entrada;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private List<LocalDate> saida;
	
	@ManyToOne
	@JsonIgnoreProperties("cadastroFuncionario")
	private CadastroGerenciador cadastroGerenciador;


	//manter apenas em caso de aplicação do Junit
	public CadastroFuncionario(long id, String nome, String numeroPessoal, long cpf, String equipe, List<LocalDate> entrada, List<LocalDate> saida) {
		this.id = id;
		this.nome = nome;
		this.numeroPessoal = numeroPessoal;
		this.cpf = cpf;
		this.equipe = equipe;
		this.entrada = entrada;
		this.saida = saida;
	}
	//manter apenas em caso de aplicação do Junit
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
	
	public List<LocalDate> getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDate entrada) {
		this.entrada.add(entrada);
	}

	public List<LocalDate> getSaida() {
		return saida;
	}

	public void setSaida(LocalDate saida) {
		this.saida.add(saida);
	}
}