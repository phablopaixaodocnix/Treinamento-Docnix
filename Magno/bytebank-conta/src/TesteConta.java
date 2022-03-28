public class TesteConta {
    public static void main(String[] args) {
        ContaCorrente contaUm = new ContaCorrente(3123,34234);
        contaUm.depositar(500);

        ContaPoupanca contaDois = new ContaPoupanca(200,2999);
        contaDois.depositar(200);

//        System.out.println(contaUm.getNumeroDaAgencia());
//        System.out.println(contaDois.getNumeroDaAgencia());
//
//        System.out.println();

        System.out.println(contaUm.getSaldo());
        System.out.println(contaDois.getSaldo());

        System.out.println();

        contaUm.transferir(contaDois,200);
        System.out.println(contaUm.getSaldo());
        System.out.println(contaDois.getSaldo());
    }
}
