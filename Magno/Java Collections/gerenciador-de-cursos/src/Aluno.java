public class Aluno {
    private String nome;
    private int numeroDeMatricula;

    public Aluno(String name, int numeroDeMatricula){
        this.nome = name;
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public String getNome(){
        return this.nome;
    }

    public int getNumeroDeMatricula(){
        return this.numeroDeMatricula;
    }

}
