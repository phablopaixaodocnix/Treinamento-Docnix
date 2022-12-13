package validacoes;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import validacoes.Exceptions.CpfInvalidoException;
import validacoes.Exceptions.EmailInvalidoException;
import validacoes.Exceptions.PoucosContatosExcepption;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidarFormularioTest extends TestCase {
    private String emailValido = "phablo@gmail.com";
    private String emailInvalido = "phablogmail.com";
    private String cpfValido = "707.783.581-27";
    private String cpfInvalido = "123.456.789-12";
    private  String cpfNumerosRepitidos = "111.111.111-11";
    private int quantidadeValidaDeContatos = 4;
    private int quantidadeInvalidaDeContatos = -3;

    ValidarFormulario validarFormulario = new ValidarFormulario();

    @Test
    public void testEmailValido(){
        assertTrue(validarFormulario.validarEmail(emailValido));
    }
    @Test
    public void testEmailInvalido(){
        assertThrows(EmailInvalidoException.class, ()->validarFormulario.validarEmail(emailInvalido));
    }

    @Test
    public void testeCpfValido() {
        assertTrue(validarFormulario.validarCpf(cpfValido));
    }
    @Test
    public void testeCpfNumerosRepitidos() {
        assertThrows(CpfInvalidoException.class,() ->validarFormulario.validarCpf(cpfNumerosRepitidos));
    }
    @Test
    public void testeCpfInalido() {
        assertThrows(CpfInvalidoException.class,() ->validarFormulario.validarCpf(cpfInvalido));
    }

    @Test
    public void quantidadeDeContatosValida(){
        assertTrue(validarFormulario.validarQuantidadeDeContatos(quantidadeValidaDeContatos));
    }
    @Test
    public void quantidadeDeContatosInvalida(){
        assertThrows(PoucosContatosExcepption.class, ()->validarFormulario.validarQuantidadeDeContatos(quantidadeInvalidaDeContatos));
    }

}
