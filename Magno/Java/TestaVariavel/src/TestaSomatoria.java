public class TestaSomatoria {
    public static void main(String[] args) {
        int contador = 0;
        int total = 0;
        while (contador<=10){
            total += contador;
            contador++;
            System.out.println("QUANTIDADE DE VEZES:" + contador);
            System.out.println("SOMADO:"+total);
        }
    }
}
