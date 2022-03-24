

public class TesteBanco {
    public static void main(String[] args) {
        //Cliente paulo = new Cliente();
        //paulo.profissao = "Ator";
        //paulo.cpf = "66666666666";
        //paulo.nome = "Paulão da Massa";

        Conta contaDoPaulo = new Conta();
        contaDoPaulo.saldo = 1000;

        //System.out.println(contaDoPaulo.saldo);

        contaDoPaulo.titular = new Cliente();
        //System.out.println(contaDoPaulo.titular.nome = "André");
        //System.out.println(contaDoPaulo.titular.cpf = "666666666");
        //System.out.println(tipoEnum.PROFESSOR.toString());

        System.out.println(Profissoes.ACOGUEIRO);
    }
}
