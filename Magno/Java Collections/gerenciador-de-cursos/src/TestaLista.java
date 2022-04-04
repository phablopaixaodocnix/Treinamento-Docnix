import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TestaLista {
    public static void main(String[] args) {
        String aula1 = "Primeira Aula";
        String aula2 = "Segunda Aula";
        String aula3 = "Terceira Aula";

        ArrayList<String> aulas = new ArrayList<>();

        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        Collections.sort(aulas);

        System.out.println(aulas);

    }
}
