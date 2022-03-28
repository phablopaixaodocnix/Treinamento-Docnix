public class ControleBonificacao {

    private double soma;

    public double bonificacao(Funcionario a){
        double boni = a.bonificacao();
        return this.soma += boni;
    }
}
