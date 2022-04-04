import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestaCurso {
    public static void main(String[] args) {
        Curso primeiroCurso = new Curso("Primeiro Curso", "Paulo");

        primeiroCurso.adiciona(new Aula(20,"Nome aula"));
        primeiroCurso.adiciona(new Aula(15, "Outra aula"));
        primeiroCurso.adiciona(new Aula(13,"Mais uma aula"));

        List<Aula> aulasImutaveis = primeiroCurso.getAulas();
        System.out.println(aulasImutaveis);

        List<Aula> aulas = new ArrayList<>(aulasImutaveis);
        Collections.sort(aulas);
        System.out.println(aulas);

        System.out.println(primeiroCurso.getTempo());

        System.out.println(primeiroCurso);
    }
}
