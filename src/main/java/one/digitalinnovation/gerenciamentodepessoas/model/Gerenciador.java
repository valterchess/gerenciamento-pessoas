package one.digitalinnovation.gerenciamentodepessoas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_cadastroGerenciador")
public class Gerenciador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	private String nome;

	@NotBlank(message = "O atributo número pessoal é obrigatório!")
	private String numeroPessoal;

	@CPF
	private long cpf;
	//adicionar um atributo tipo do tipo "Tipo.class"
	@NotBlank(message = "O atributo responsável equipe é obrigatório!")
	private String respEquipe;
	
	@NotNull(message = "O atributo Usuário é Obrigatório!")
	@Email(message = "O atributo Usuário deve ser um email!")
	private String usuario;

	@NotBlank(message = "O atributo Senha é Obrigatória!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;

	@OneToMany(mappedBy = "gerenciador",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("gerenciador")
	private List<Funcionario> funcionario;

	public Gerenciador(long id, String nome, String numeroPessoal, long cpf, String respEquipe, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.numeroPessoal = numeroPessoal;
		this.cpf = cpf;
		this.respEquipe = respEquipe;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Gerenciador() {
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

	public List<Funcionario> getCadastroFuncionario() {
		return funcionario;
	}

	public void setCadastroFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	

}
