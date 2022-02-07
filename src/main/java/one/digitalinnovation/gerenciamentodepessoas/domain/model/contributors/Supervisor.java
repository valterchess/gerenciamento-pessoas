package one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import one.digitalinnovation.gerenciamentodepessoas.domain.model.locations.Predio;

@Entity
@Table(name = "tb_supervisor")
public class Supervisor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	private String nome;

	@NotBlank(message = "O atributo Email é Obrigatório!")
	@Email(message = "O atributo precisa ser um email.")
	private String email;

	@NotBlank(message = "O atributo Senha é Obrigatória!")
	@Size(min = 8, max = 30, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;

	@ManyToOne
	@JsonIgnoreProperties("supervisor")
	private Gerente gerente;

	@OneToMany(mappedBy = "supervisor",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("supervisor")
	private List<Funcionario> funcionario;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "predio_id")
	private Predio predio;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}
}