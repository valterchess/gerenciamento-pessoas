package br.com.nesher.gerenciamentopessoas.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_gerente")
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(min = 3, max = 60, message = "O atributo nome precisa ter entre 3 e 60 caracteres.")
    private String nome;

    @NotBlank(message = "O atributo email é obrigatório.")
    @Email(message = "O atributo precisa ser um E-mail.")
    private String email;

    @NotBlank(message = "O atributo senha é obrigatório.")
    @Size(min = 8, max = 30, message = "A senha precisa ter entre 8 e 30 carácteres")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conjunto_id")
    private Conjunto conjunto;

    @OneToMany(mappedBy = "gerente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("gerente")
    private List<Supervisor> supervisor;

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

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
    }

    public List<Supervisor> getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(List<Supervisor> supervisor) {
        this.supervisor = supervisor;
    }
}
