package one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import one.digitalinnovation.gerenciamentodepessoas.domain.model.locations.Predio;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	private String nome;

	private String credencial;


	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Predio predio;

	@OneToMany(mappedBy = "funcionario",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private List<Entrada> entrada;

	@OneToMany(mappedBy = "funcionario",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("funcionario")
	private List<Saida> saida;
	
	@ManyToOne
	@JsonIgnoreProperties("funcionario")
	private Supervisor supervisor;

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

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
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

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
}