package Cadastro;

public class Contato {
    public int getIdContato() {
        return IdContato;
    }

    public void setIdContato(int idContato) {
        IdContato = idContato;
    }

    private int IdContato;
    private String nomeAlternativo;
    private String emailAlternativo;
    private String telefoneAlternativo;

    public String getNomeAlternativo() {
        return nomeAlternativo;
    }

    public void setNomeAlternativo(String nomeAlternativo) {
        this.nomeAlternativo = nomeAlternativo;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

}
