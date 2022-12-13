package validacoes.Exceptions;

public class EmailInvalidoException extends RuntimeException{
    public EmailInvalidoException(){
        super();
    }

    public EmailInvalidoException(String errorMessage){
        super(errorMessage);
    }
}
