package one.digitalinnovation.gerenciamentodepessoas.model;

import java.util.List;

public class LoginGerenciador {

	private long id;

	private String nome;

	private String numeroPessoal;

	private long cpf;

	private String respEquipe;
	
	private String usuario;

	private String senha;

	private List<CadastroFuncionario> cadastroFuncionario;
	
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

	public String getRespEquipe() {
		return respEquipe;
	}

	public void setRespEquipe(String respEquipe) {
		this.respEquipe = respEquipe;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<CadastroFuncionario> getCadastroFuncionario() {
		return cadastroFuncionario;
	}

	public void setCadastroFuncionario(List<CadastroFuncionario> cadastroFuncionario) {
		this.cadastroFuncionario = cadastroFuncionario;
	}

}
