package MeuPacote;

public class Endereço {
	private String cidade;
	private String bairro;
	private String rua;
	private int quadra;
	private int casa;
	private int cep;
	private int lote;
	private int numero;
	private String uf;
	
	public Endereço() {}
	public Endereço(String cidade, String bairro, String rua, int quadra, int casa, int cep, int lote, int numero, String uf) {
		super();
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.quadra = quadra;
		this.casa = casa;
		this.cep = cep;
		this.lote = lote;
		this.numero = numero;
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getQuadra() {
		return quadra;
	}
	public void setQuadra(int quadra) {
		this.quadra = quadra;
	}
	public int getCasa() {
		return casa;
	}
	public void setCasa(int casa) {
		this.casa = casa;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public int getLote() {
		return lote;
	}
	public void setLote(int lote) {
		this.lote = lote;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	
}
