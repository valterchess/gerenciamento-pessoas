package br.com.magnasistemas.gerenciamentodepessoas.domain.dto.contributors;

import java.util.List;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Saida;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Endereco;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Predio;
import br.com.magnasistemas.gerenciamentodepessoas.shared.utils.ConversorDeEntrada;

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
