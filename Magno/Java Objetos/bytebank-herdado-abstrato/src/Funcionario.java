public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;

    public Funcionario(){

    }

    public Funcionario(String nome, String cpf, double salario){
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public double bonificacao(){
        return salario * 0.10;
    }

    public void setSalariosalario (double valorSalario){
        this.salario = valorSalario;
    }

    public double getSalario(){
        return this.salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
