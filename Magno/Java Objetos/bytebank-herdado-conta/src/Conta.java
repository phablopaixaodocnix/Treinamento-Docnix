public class Conta {
    private double saldo;
    private int agencia;
    private int numeroDaConta;
    private Cliente titular;

    public Conta(){

    }

    public Conta(int agencia, int numeroDaConta){
        this.agencia = agencia;
        this.numeroDaConta = numeroDaConta;
    }

    public double sacar(double valor){
        return saldo-=valor;
    }

    public double depositar(double valor){
        return saldo+=valor;
    }



    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
}
