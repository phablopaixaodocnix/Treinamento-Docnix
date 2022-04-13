package modelo;

public class Produto {
	private Integer id;
	private String nome;
	private String descricao;
	
	public Produto(String nome, String descricao){
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return String.format("O produto criado foi: %d, %s, %s", 
				this.id, this.nome, this.descricao);
	}
	
}
