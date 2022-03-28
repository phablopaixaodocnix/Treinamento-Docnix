public class TesteFuncionario {
    public static void main(String[] args) {
        Funcionario cleiton = new Gerente(2002);
        cleiton.setSalariosalario(2000);

        System.out.println(cleiton.bonificacao());
    }
}
