package validacoes.Exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(){
        super();
    }

    public CpfInvalidoException(String errorMessage){
        super(errorMessage);
    }
}
