package Cadastro;

import java.util.ArrayList;

public class Cadastro {

    private String ID;
    private String nome;
    private String email;
    private String cpf;
    private String cep;
    private String rua;
    private String cidade;
    private String nomeContatoPrincipal;
    private String emailContatoPrincipal;
    private String telefoneContatoPrincipal;

    private ArrayList<Contato> arrayListContato = new ArrayList();

    public ArrayList<Contato> getArrayListContato() {
        return arrayListContato;
    }

    public void setArrayListContato(ArrayList<Contato> arrayListContato) {
        this.arrayListContato = arrayListContato;
    }


    public String getNomeContatoPrincipal() {
        return nomeContatoPrincipal;
    }

    public void setNomeContatoPrincipal(String nomeContatoPrincipal) {
        this.nomeContatoPrincipal = nomeContatoPrincipal;
    }

    public String getEmailContatoPrincipal() {
        return emailContatoPrincipal;
    }

    public void setEmailContatoPrincipal(String emailContatoPrincipal) {
        this.emailContatoPrincipal = emailContatoPrincipal;
    }

    public String getTelefoneContatoPrincipal() {
        return telefoneContatoPrincipal;
    }

    public void setTelefoneContatoPrincipal(String telefoneContatoPrincipal) {
        this.telefoneContatoPrincipal = telefoneContatoPrincipal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        int ctd = 0;
        StringBuilder stringb = new StringBuilder();
        stringb.append("ID: ").append(getID()).append("\n");
        stringb.append("Nome: ").append(getNome()).append("\n");
        stringb.append("CPF: ").append(getCpf()).append("\n");
        stringb.append("CEP: ").append(getCep()).append("\n");
        stringb.append("Rua: ").append(getRua()).append("\n");
        stringb.append("Cidade: ").append(getCidade()).append("\n");
        stringb.append("\nContatos Alternativos: ").append("\n");
        for (int i = 0; i < arrayListContato.size(); i++) {
            Contato contatoAlternativo = arrayListContato.get(i);
                stringb.append("\nContato Alternativo : ").append(ctd).append("\n");
                stringb.append("Nome: ").append(contatoAlternativo.getNomeAlternativo()).append("\n");
                stringb.append("Email: ").append(contatoAlternativo.getEmailAlternativo()).append("\n");
                stringb.append("Telefone: ").append(contatoAlternativo.getTelefoneAlternativo()).append("\n");
                ctd++;
            }
        ctd = 0;
        return stringb.toString();
    }

    ;
}
