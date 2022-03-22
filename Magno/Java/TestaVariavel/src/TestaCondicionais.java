public class TestaCondicionais {
    public static void main(String[] args) {
        System.out.println("TESTE DE CONDICIONAIS\n");
        int idade = 17;
        int numeroDePessoas = 3;

        //if (idade >= 18){
            //System.out.println("Você pode entrar");
        //}else if (numeroDePessoas >=2){
            //System.out.println("Pode entrar pois está acompanhado");
        //}else{
            //System.out.println("Não pode entrar");
        //}

        if (idade>=18) System.out.println("Entra");
        else if(numeroDePessoas>=2) System.out.println("Acompanhado "+
                "de responsável");
        else System.out.println("Não entra");

        //sout
    }
}
