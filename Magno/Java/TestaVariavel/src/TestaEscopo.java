public class TestaEscopo {
    public static void main(String[] args) {
        int idade = 18;
        int quantidadePessoas = 3;
        boolean acompanhado;

        if (quantidadePessoas >=2){
            acompanhado = true;
        } else acompanhado = false;

        System.out.println("A expressão booleana é: " + acompanhado);

        if (idade >= 18 && acompanhado) {
            System.out.println("Pode entrar");
        } else {
            System.out.println("Não pode entrar");
        }
    }
}
