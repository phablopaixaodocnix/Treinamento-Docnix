import java.util.ArrayList;
import java.util.Collections;

public class TestaListaDeAula {
    public static void main(String[] args) {
        Aula a1 = new Aula(10, "Crimeira Aula");
        Aula a2 = new Aula(20, "Fegunda Aula");
        Aula a3 = new Aula(15, "Aerceira Aula");

        ArrayList<Aula> aulas = new ArrayList<>();
        aulas.add(a1);
        aulas.add(a2);
        aulas.add(a3);

        Collections.sort(aulas);

        System.out.println(aulas);
    }
}
