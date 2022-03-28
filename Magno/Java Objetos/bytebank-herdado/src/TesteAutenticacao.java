public class TesteAutenticacao {
    public static void main(String[] args) {
        Gerente nomeGerente = new Gerente();
        nomeGerente.setSenha(0220);

        System.out.println(nomeGerente.autentica(nomeGerente));

    }
}
