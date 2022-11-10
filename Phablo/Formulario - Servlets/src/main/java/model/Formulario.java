package model;

import javax.persistence.*;
import java.util.List;

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

  public void imprimirFormulario() {
    System.out.println("   Dados pessoais");
    System.out.println("  Nome: " + this.nome);
    System.out.println("  Cpf: " + this.cpf);
    System.out.println("  Email: " + this.email);
    System.out.println();
    System.out.println("   Endereço");
    System.out.println("  Cidade: " + this.endereco.getCidade());
    System.out.println("  Bairro: " + this.endereco.getBairro());
    System.out.println("  Rua: " + this.endereco.getRua());
    System.out.println("  Quadra: " + this.endereco.getQuadra() + "  Casa: " + this.endereco.getCasa());
    System.out.println("  Cep: " + this.endereco.getCep() + "  Lote: " + this.endereco.getLote());
    System.out.println("  Numero: " + this.endereco.getNumero() + "  UF: " + this.endereco.getUf());
    for (int i = 0; i < contatos.size(); i++) {
      System.out.println();
      System.out.println("   Contato " + (i + 1));
      System.out.println("  Nome:" + contatos.get(i).getNome());
      System.out.println("  Email:" + contatos.get(i).getEmail());
      System.out.println("  Telefone:" + contatos.get(i).getTelefone());
    }
  }
}
