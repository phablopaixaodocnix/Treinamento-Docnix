public class TesteFuncionario {
    public static void main(String[] args) {
        Gerente cleiton = new Gerente(2002);
        cleiton.setCpf("023848203");
        cleiton.setNome("Cleiton");
        cleiton.setSalariosalario(2000);
        System.out.println(cleiton.getCpf());
        System.out.println(cleiton.getNome());
        System.out.println(cleiton.getSalario());

        boolean autentica = cleiton.autenticaSenha(2002);
        System.out.println(autentica);
        System.out.println(cleiton.bonificacao());
    }
}
