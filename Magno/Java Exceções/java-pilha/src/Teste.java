import java.awt.font.NumericShaper;
import java.text.NumberFormat;

public class Teste {
    public static void main(String[] args) {
        NumberFormat x = NumberFormat.getCurrencyInstance();

        int numero = 20;
        System.out.println(x.format(numero));
    }
}
