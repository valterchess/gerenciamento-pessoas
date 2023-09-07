package br.com.nesher.gerenciamentopessoas.domain.vo;

public class EntradaVO {

	private long id;

	private String entrada;
	
	private String nomeFuncionario;
	private String credencialFuncionario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getCredencialFuncionario() {
		return credencialFuncionario;
	}

	public void setCredencialFuncionario(String credencialFuncionario) {
		this.credencialFuncionario = credencialFuncionario;
	}

}
