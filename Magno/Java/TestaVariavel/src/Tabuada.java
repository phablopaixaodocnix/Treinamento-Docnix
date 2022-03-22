public class Tabuada {
    public static void main(String[] args) {
        for (int numero = 1; numero <= 10; numero++){
            for (int multiplicador = 0; multiplicador <= 10; multiplicador++){
                System.out.print(numero*multiplicador+" ");
            }
            System.out.println();
        }
    }
}
