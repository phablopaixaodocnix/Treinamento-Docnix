public class Conta {
    double saldo;
    int agencia;
    int numeroDaConta;
    Cliente titular;

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

}
