public class Gerente extends Funcionario implements SistemaInterno{
    private int senha;

    public Gerente() {;
    }


    public boolean autentica(int senha){
        if(this.senha==senha){
            System.out.println("Senha correta");
            return true;
        } else {
            System.out.println("Senha incorreta");
            return false;
        }
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    @Override
    public double bonificacao() {
        return super.bonificacao() + super.getSalario();
    }

    @Override
    public boolean autentica(Gerente g) {
        {
            boolean autenticou = g.autentica(this.senha);
            if (autenticou){
                System.out.println("Autenticou");
                return true;
            }
            else{
                System.out.println("Não autenticou");
                return false;
            }
        }

    }
}
