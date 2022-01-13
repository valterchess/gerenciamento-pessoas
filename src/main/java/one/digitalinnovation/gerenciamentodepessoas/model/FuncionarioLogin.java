package one.digitalinnovation.gerenciamentodepessoas.model;

import java.util.Date;

public class FuncionarioLogin {

	private long id;

	private String nome;

	private String numeroPessoal;

	private long cpf;

	private String equipe;

	private Date entrada;

	private Date saida;

	private Gerenciador gerenciador;

	private String token;

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

	public Gerenciador getCadastroGerenciador() {
		return gerenciador;
	}

	public void setCadastroGerenciador(Gerenciador gerenciador) {
		this.gerenciador = gerenciador;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
