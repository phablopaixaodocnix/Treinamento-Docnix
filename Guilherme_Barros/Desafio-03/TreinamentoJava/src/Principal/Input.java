package Principal;


import Cadastro.Contato;

import java.util.ArrayList;

public class Input {
    private String nome;
    private String email;
    private String cpf;
    private String cep;
    private String rua;
    private String cidade;
    private String nomeContatoPrincipal;
    private String emailContatoPrincipal;
    private String telefoneContatoPrincipal;
    private ArrayList<Contato> arrayList;

    public ArrayList<Contato> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Contato> arrayList) {
        this.arrayList = arrayList;
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
}
