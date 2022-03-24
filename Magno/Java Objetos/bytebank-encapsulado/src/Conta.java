public class Conta {
    private double saldo;
    private int agencia;
    private int numeroDaConta;
    private Cliente titular;

    public Conta (int agencia, int numeroDaConta){
        this.agencia = agencia;
        this.numeroDaConta = numeroDaConta;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        else{
            return false;
        }

    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean transfere(double valor, Conta contaDestino) {
        if (valor <= saldo) {
            saldo -= valor;
            contaDestino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

     public double getSaldo (){
        return saldo;
    }

    public int getNumeroDaConta(){
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numero){
        numeroDaConta = numero;
    }

    public int getAgencia(){
        return agencia;
    }

    public void setAgencia(int agencia){
        this.agencia = agencia;
    }

}
