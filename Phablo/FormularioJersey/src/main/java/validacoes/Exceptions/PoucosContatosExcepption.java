package validacoes.Exceptions;

public class PoucosContatosExcepption extends RuntimeException{

    public PoucosContatosExcepption(){
        super();
    }

    public PoucosContatosExcepption(String errorMessage){
        super(errorMessage);
    }

}
