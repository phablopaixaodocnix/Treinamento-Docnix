import java.util.*;

public class TestaAlunos {
    public static void main(String[] args) {
        Collection<String> alunos = new HashSet<>();
//        alunos.add("Aluno A");
//        alunos.add("Aluno B");
//        alunos.add("Aluno C");
//        alunos.add("Aluno D");
//        alunos.add("Aluno E");
        alunos.add("Aluno A");
        alunos.add("Aluno B");
        alunos.add("Aluno C");
        alunos.add("Aluno D");

//        for (String aluno : alunos) {
//            System.out.println  (aluno);
//        }
        boolean alunoTalMatriculado = alunos.contains("Magno Levi");
        System.out.println(alunoTalMatriculado);

        //alunos.remove("Aluno A");
        //System.out.println(alunos);

        List<String> alunosEmLista = new ArrayList<>(alunos);
        System.out.println(alunosEmLista.get(0));

    }
}
