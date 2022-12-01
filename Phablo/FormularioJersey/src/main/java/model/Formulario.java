package model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static model.Contato.contatoJsonToObject;
import static model.Endereco.enderecoJsonToObject;

@Entity
@Table(name = "formularios")
public class Formulario implements AcoesContatos {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idFormulario")
  private int idFormulario;
  private String nome;
  private String email;
  private String cpf;
  private String escolaridade;
  private int quantidadeDeContatosNesteFormulario;
  @OneToOne(mappedBy = "formulario",cascade = CascadeType.ALL,orphanRemoval = true)
  private Endereco endereco;
  @OneToMany(mappedBy = "formulario",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
  private List<Contato> contatos;

  public Formulario(){
  }

  public Formulario(String nome, String email, String cpf, Endereco endereco, String escolaridade,
                    List<Contato> contatos) {
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.escolaridade = escolaridade;
    this.endereco = endereco;
    this.contatos = contatos;
    if(contatos != null)
      this.quantidadeDeContatosNesteFormulario = contatos.size();
  }
  public Formulario(String nome, String email, String cpf, String escolaridade) {
    this.idFormulario = idFormulario;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.escolaridade = escolaridade;
    this.endereco = endereco;
    if(contatos != null)
      this.quantidadeDeContatosNesteFormulario = contatos.size();
  }

  public Formulario(int idFormulario, String nome, String email, String cpf, Endereco endereco, String escolaridade,
                    List<Contato> contatos) {
    this.idFormulario = idFormulario;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.escolaridade = escolaridade;
    this.endereco = endereco;
    this.contatos = contatos;
    if(contatos != null)
      this.quantidadeDeContatosNesteFormulario = contatos.size();
  }

  public Formulario(int idFormulario, String nome, String email, String cpf, Endereco endereco, String escolaridade) {
    this.idFormulario = idFormulario;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.escolaridade = escolaridade;
    this.endereco = endereco;
    if(contatos != null)
      this.quantidadeDeContatosNesteFormulario = contatos.size();
  }
  public Formulario(int idFormulario, String nome, String email, String cpf, String escolaridade) {
    this.idFormulario = idFormulario;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.escolaridade = escolaridade;
    this.endereco = endereco;
    if(contatos != null)
      this.quantidadeDeContatosNesteFormulario = contatos.size();
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

  public String getEscolaridade() {
    return this.escolaridade;
  }

  public void setEscolaridade(String escolaridade) {
    this.escolaridade = escolaridade;
  }

  public Endereco getEndereco() {
    return this.endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public List<Contato> getContatos() {
    return this.contatos;
  }

  public void setContatos(List<Contato> contatos) {
    this.contatos = contatos;
  }

  public int getQuantidadeDeContatosNesteFormulario() {
    return this.quantidadeDeContatosNesteFormulario;
  }

  public void setQuantidadeDeContatosNesteFormulario(int quantidadeDeContatosNesteFormulario) {
    this.quantidadeDeContatosNesteFormulario = quantidadeDeContatosNesteFormulario;
  }

  public int getIdFormulario(){
    return this.idFormulario;
  }

  public void setIdFormulario(int id) {
    this.idFormulario = id;
  }

  @Override
  public boolean adicionarContato(Contato c) {
    contatos.add(c);
    quantidadeDeContatosNesteFormulario++;
    return true;
  }

  @Override
  public boolean removerContato(int indicieASerRemovido) {
    quantidadeDeContatosNesteFormulario--;
    contatos.remove(indicieASerRemovido);
    return true;
  }


  public static Formulario formularioJsonToObject(JSONObject jsonObject, boolean enviar){
    String nome = jsonObject.getString("nome");
    String email = jsonObject.getString("email");
    String cpf = jsonObject.getString("cpf");
    Endereco endereco = enderecoJsonToObject((JSONObject) jsonObject.get("endereco"));
    String escolaridade = jsonObject.getString("escolaridade");

    List<Contato> contatos = new ArrayList<>();
    JSONArray contatosJson = (JSONArray) jsonObject.get("contatos");
    for(int i = 0 ; i < contatosJson.length() ; i++){
      contatos.add( contatoJsonToObject((JSONObject) contatosJson.get(i)) );
    }

    Formulario formulario = null;
    if(enviar)
        formulario = new Formulario(nome,email,cpf,endereco,escolaridade,contatos);
    else {
      int idFormulario = jsonObject.getInt("idFormulario");
      formulario = new Formulario(idFormulario, nome, email, cpf, endereco, escolaridade, contatos);
    }
    return formulario;
  }

  public static JSONArray formularioListToJsonArray(List<Formulario> formularios){
    JSONArray jsonArray = new JSONArray();
    for(Formulario f: formularios){
      JSONObject jsonObject = new JSONObject(f);
      jsonArray.put(jsonObject);
    }
    return jsonArray;
  }

}
