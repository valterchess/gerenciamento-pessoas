package br.com.magnasistemas.gerenciamentodepessoas.domain.vo.contributors;

import java.time.LocalDateTime;

public class EntradaVO {
	private Long id;
	private LocalDateTime entrada;
	private String nomeDoFuncionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public String getNomeDoFuncionario() {
		return nomeDoFuncionario;
	}

	public void setNomeDoFuncionario(String nomeDoFuncionario) {
		this.nomeDoFuncionario = nomeDoFuncionario;
	}

}
