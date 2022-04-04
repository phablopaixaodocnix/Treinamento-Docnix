public class TestaCursoComAluno {
    public static void main(String[] args) {

        Curso nomeDoCurso = new Curso("Curso A", "Paulo");

        nomeDoCurso.adiciona(new Aula(20,"Aula 1"));
        nomeDoCurso.adiciona(new Aula(15,"Aula 2"));
        nomeDoCurso.adiciona(new Aula(5, "Aula 3"));

        Aluno aluno1 = new Aluno("João", 1156);
        Aluno aluno2 = new Aluno("André", 1546);
        Aluno aluno3 = new Aluno("Gomes", 1780);

        nomeDoCurso.matricula(aluno1);
        nomeDoCurso.matricula(aluno2);
        nomeDoCurso.matricula(aluno3);

        nomeDoCurso.getAlunos().forEach(a ->{
            System.out.println(a.getNome());
        });

        System.out.println(nomeDoCurso.estaMatriculado(aluno1));

    }
}
