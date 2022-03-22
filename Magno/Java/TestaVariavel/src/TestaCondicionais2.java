public class TestaCondicionais2 {
    public static void main(String[] args) {
        int idade = 18;
        int quantidadePessoas = 3;
        boolean acompanhado = quantidadePessoas>=2;

        System.out.println("Acompanhado é uma expressão booleana: " + acompanhado);

        if(idade>=18 && acompanhado) {
            System.out.println("Pode entrar");
        }
        else{
            System.out.println("Não pode entrar");
        }
    }
}
