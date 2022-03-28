public class TestaBanco {
    public static void main(String[] args) {
        Conta conta = new Conta(20, 2000);
        System.out.println(conta.getAgencia());
        System.out.println(conta.getNumeroDaConta());
    }
}
