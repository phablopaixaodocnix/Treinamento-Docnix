public class ContaPoupanca extends Conta{

    public ContaPoupanca(){
    }

    public ContaPoupanca(int numeroDaConta, int numeroDaAgencia){
        super(numeroDaConta, numeroDaAgencia);
    }

    @Override
    public double sacar(double valor) {
        return super.sacar(valor);
    }

    @Override
    public double depositar(double valor) {
        return super.depositar(valor);
    }
}
