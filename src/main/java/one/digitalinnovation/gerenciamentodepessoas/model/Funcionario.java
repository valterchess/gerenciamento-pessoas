package one.digitalinnovation.gerenciamentodepessoas.model;

import java.util.Date;

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
@Table (name = "tb_funcionario")
public class Funcionario {

	@Id	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank (message = "O atributo nome é obrigatório!")
	private String nome;
	
	@NotBlank (message = "O atributo número ID é obrigatório!")
	private String numeroId;
	
	@CPF
	private int cpf;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date entrada;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date saida;
    
	@ManyToOne
	@JsonIgnoreProperties("funcionario")
	private Gerenciador gerenciador;

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

	public String getNumeroId() {
		return numeroId;
	}

	public void setNumeroId(String numeroId) {
		this.numeroId = numeroId;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
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

	public Gerenciador getGerenciador() {
		return gerenciador;
	}

	public void setGerenciador(Gerenciador gerenciador) {
		this.gerenciador = gerenciador;
	}
	
	
	
    
}