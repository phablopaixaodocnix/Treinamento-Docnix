public class Gerente extends Funcionario{
    private int senha;

    public Gerente(int senha) {
        this.senha = senha;
    }

    public boolean autenticaSenha(int senha){
        if (this.senha == senha){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public double bonificacao() {
        return super.getSalario();
    }
}
