package modelo;

public class calculadorDeImposto {

    private double totalImposto;

    public void registra(Tributavel t){
        double valor = t.pegarValorImposto();
        this.totalImposto += valor;
    }

    public double getTotalImposto() {
        return totalImposto;
    }
}
