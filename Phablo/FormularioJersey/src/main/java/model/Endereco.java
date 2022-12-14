package model;

import org.json.JSONObject;

import javax.persistence.*;
@Entity
@Table(name = "enderecos")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEndereco;
	private String cidade;
	private String bairro;
	private String rua;
	private int quadra;
	private int casa;
	private String cep;
	private int lote;
	private int numero;
	private String uf;
	@OneToOne
	@JoinColumn(name="idFormulario")
	private Formulario formulario;

	private int idFormularioNotFk;

	public Endereco() {
	}

	public Endereco(int idEndereco, String cidade, String bairro, String rua, int quadra, int casa, String cep, int lote, int numero,
					String uf) {
		this.idEndereco = idEndereco;
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
	public Endereco(String cidade, String bairro, String rua, int quadra, int casa, String cep, int lote, int numero,
					String uf) {
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
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

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public int getIdFormularioNotFk() {
		return idFormularioNotFk;
	}

	public void setIdFormularioNotFk(int idFormularioNotFk) {
		this.idFormularioNotFk = idFormularioNotFk;
	}

	public static Endereco enderecoJsonToObject(JSONObject jsonObject){
		String cidade = jsonObject.getString("cidade");
		String bairro = jsonObject.getString("bairro");
		String rua = jsonObject.getString("rua");
		int quadra = converterParaIntegerConsiderandoCasosDeStringNula(jsonObject.getString("quadra"));
		int casa = converterParaIntegerConsiderandoCasosDeStringNula(jsonObject.getString("casa"));
		String cep = jsonObject.getString("cep");
		int lote = converterParaIntegerConsiderandoCasosDeStringNula(jsonObject.getString("lote"));
		int numero = converterParaIntegerConsiderandoCasosDeStringNula(jsonObject.getString("numero"));
		String uf = jsonObject.getString("uf");
		return new Endereco(cidade,bairro,rua,quadra,casa,cep,lote,numero,uf);
	}

	private static Integer converterParaIntegerConsiderandoCasosDeStringNula(String str) {
		try{
			return Integer.valueOf(str);
		} catch (NumberFormatException exception){
			return 0;
		}
	}
}
