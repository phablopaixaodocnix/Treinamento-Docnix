package model;

import javax.persistence.*;

@Entity
@Table(name = "contatos")
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContato;
	private String nome;
	private String telefone;
	private String email;

	@ManyToOne
	@JoinColumn(name="idFormulario")
	private Formulario formulario;

	public Contato() {
	}

	public Contato(String nome, String telefone, String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
}
