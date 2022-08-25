package Principal;


import Cadastro.Contato;

import java.util.ArrayList;

public class Input {
    private String nome;
    private String email;
    private long cpf;
    private long cep;
    private String rua;
    private String cidade;
    private String nomeContatoPrincipal;
    private String emailContatoPrincipal;
    private long telefoneContatoPrincipal;
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

    public long getTelefoneContatoPrincipal() {
        return telefoneContatoPrincipal;
    }

    public void setTelefoneContatoPrincipal(long telefoneContatoPrincipal) {
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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
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
