public class Fluxo {
    public static void main(String[] args) {
        System.out.println("Início do main");
        metodo1();
        System.out.println("Fim do main");
    }

    public static void metodo1(){
        System.out.println("Início do método 1");
        metodo2();
        System.out.println("Fim o método 1");
    }

    public static void metodo2(){
        System.out.println("Início do método 2");
        for (int i = 1; i<= 5; i++){
            System.out.println(i);
            try{
                int a = i/0;
                System.out.println(a);
            } catch (ArithmeticException ex){
                throw new MinhaExcecao("Deu ruim");
                //System.out.println("Exceção aritmética");
            }

        }
        System.out.println("Fim do método 2");
    }

}
