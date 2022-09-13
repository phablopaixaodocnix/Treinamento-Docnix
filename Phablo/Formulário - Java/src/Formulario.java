import java.util.ArrayList;

public class Formulario implements AçõesContatos{
	private String nome;
	private String email;
	private String cpf;
	private String escolaridade;
	private Endereço endereço = new Endereço();
	private ArrayList<Contato> contatos = new ArrayList<Contato>();
	private int quantidadeDeContatosNesteFormulario;
	static int quantidadeTotalDeFormularios = 0;

	public Formulario(String nome, String email, String cpf, Endereço endereço, String escolaridade, ArrayList<Contato> contatos) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.endereço = endereço;
		this.escolaridade = escolaridade;
		this.contatos = contatos;
		this.quantidadeDeContatosNesteFormulario = contatos.size();
		quantidadeTotalDeFormularios++;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Endereço getEndereço() {
		return this.endereço;
	}
	public void setEndereço(Endereço endereço) {
		this.endereço = endereço;
	}
	public String getEscolaridade() {
		return this.escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public ArrayList<Contato> getContatos() {
		return this.contatos;
	}
	public void setContatos(ArrayList<Contato> contatos) {
		this.contatos = contatos;
	}

	public int getQuantidadeDeContatosNesteFormulario() {
		return this.quantidadeDeContatosNesteFormulario;
	}
	
	
	@Override
	public boolean adicionarContato(Contato c) {
		contatos.add(c);
		return true;
	}
	@Override
	public boolean removerContato(int indicieASerRemovido) {
		contatos.remove(indicieASerRemovido);
		return true;
	}
	

}
