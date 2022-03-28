public class Conta {
    private int numeroDaAgencia;
    private int numeroDaConta;
    private double saldo;

    public Conta(){

    }

    public Conta(int numeroDaConta, int numeroDaAgencia){
        this.numeroDaConta = numeroDaConta;
        this.numeroDaAgencia = numeroDaAgencia;
    }

    public double depositar(double valor){
        return saldo+=valor;
    }

    public double sacar(double valor){
        return saldo-=valor;
    }

    public void transferir(Conta destino, double valor){
        saldo-=valor;
        destino.depositar(valor);
    }

    public int getNumeroDaAgencia() {
        return numeroDaAgencia;
    }

    public void setNumeroDaAgencia(int numeroDaAgencia) {
        this.numeroDaAgencia = numeroDaAgencia;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta, Conta destino) {
        this.numeroDaConta = numeroDaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
