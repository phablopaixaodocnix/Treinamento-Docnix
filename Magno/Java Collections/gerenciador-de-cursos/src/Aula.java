public class Aula implements Comparable<Aula>{
    private int tempo;
    private String nomeDaAula;

    Aula(int time, String name){
        this.tempo = time;
        this.nomeDaAula = name;
    }

    public String getNomeDaAula(){
        return nomeDaAula;
    }

    public int getTempo(){
        return tempo;
    }

    @Override
    public String toString() {
        return "(Nome da aula: " + this.nomeDaAula + " | Tempo: " + this.tempo+")";
    }

    @Override
    public int compareTo(Aula outraAula) {
        return this.nomeDaAula.compareTo(outraAula.nomeDaAula);
    }
}
