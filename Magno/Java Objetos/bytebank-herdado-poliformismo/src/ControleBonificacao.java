public class ControleBonificacao {

    private double soma;

    public double bonificacao(Funcionario a){
        double boni = a.getSalario();
        return this.soma += boni;
    }
}
