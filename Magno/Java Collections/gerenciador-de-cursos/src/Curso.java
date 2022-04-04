import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Curso {
    private String nome;
    private String instrutor;
    private List<Aula> aulas = new ArrayList<>();
    private Set<Aluno> alunos = new HashSet<>();

    public Curso(String nome, String instrutor){
        this.nome = nome;
        this.instrutor = instrutor;
    }

    public List<Aula> getAulas(){
        return aulas;
    }

    public String getNome() {
        return nome;
    }

    public String getInstrutor(){
        return instrutor;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void adiciona(Aula a){
        this.aulas.add(a);
    }

    public int getTempo(){
        int tempoTotal=0;
        for (Aula aula: aulas) {
            tempoTotal += aula.getTempo();
        }
        return tempoTotal;
    }

    public void matricula(Aluno a){
        this.alunos.add(a);
    }

    @Override
    public String toString() {
        return "(Curso: " + nome + "| Tempo total: " + getTempo()+")";
    }

    public boolean estaMatriculado(Aluno aluno) {
        return this.alunos.contains(aluno);
    }

    //
//    public void remove(Aula a){
//        this.aulas.remove(a);
//    }
}
