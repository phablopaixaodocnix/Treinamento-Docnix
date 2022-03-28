public class TesteContas {
    public static void main(String[] args) {
        ContaCorrente contaDoFulano = new ContaCorrente(1922, 3039);
        contaDoFulano.depositar(2000);

        System.out.println(contaDoFulano.getSaldo());
    }
}
