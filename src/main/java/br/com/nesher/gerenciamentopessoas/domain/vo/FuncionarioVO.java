package br.com.nesher.gerenciamentopessoas.domain.vo;

import java.util.List;

import br.com.nesher.gerenciamentopessoas.domain.converter.ConversorDeEntrada;
import br.com.nesher.gerenciamentopessoas.domain.entity.Endereco;
import br.com.nesher.gerenciamentopessoas.domain.entity.Entrada;
import br.com.nesher.gerenciamentopessoas.domain.entity.Saida;

public class FuncionarioVO {
	
	private long id;
	private String nome;
	private String credencial;
	private Endereco localTrabalho;
	private List<EntradaVO> entrada;
	private List<Saida> saida;
	private String nomeSupervisor;

	public long getId() {
		return this.id;
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

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public Endereco getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(Endereco localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public List<EntradaVO> getEntrada() {
		return entrada;
	}

	public void setEntrada(List<Entrada> entrada) {
		this.entrada = entrada.stream()
				.map(ConversorDeEntrada::converteEntradaEntityEmEntradaVO)
				.toList();
	}

	public List<Saida> getSaida() {
		return saida;
	}

	public void setSaida(List<Saida> saida) {
		this.saida = saida;
	}

	public String getNomeSupervisor() {
		return nomeSupervisor;
	}

	public void setNomeSupervisor(String nomeSupervisor) {
		this.nomeSupervisor = nomeSupervisor;
	}

}
