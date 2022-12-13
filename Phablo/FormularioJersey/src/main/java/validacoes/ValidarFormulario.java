package validacoes;

import validacoes.Exceptions.CpfInvalidoException;
import validacoes.Exceptions.EmailInvalidoException;
import validacoes.Exceptions.PoucosContatosExcepption;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidarFormulario {


    public boolean validarCpf(String cpf){
        return isValidCpf(cpf);
    }

    public boolean validarEmail(String email){
        return isValidEmail(email);
    }

    public boolean validarQuantidadeDeContatos(int qualtidadeDeContatos){
        return possui2ContatosOuMais(qualtidadeDeContatos);
    }


    private static boolean isValidCpf(String CPF) {
        String aux = CPF.replace(".","");
        String cpfApenasNumeros = aux.replace("-","");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpfApenasNumeros.equals("00000000000") ||
                cpfApenasNumeros.equals("11111111111") ||
                cpfApenasNumeros.equals("22222222222") || cpfApenasNumeros.equals("33333333333") ||
                cpfApenasNumeros.equals("44444444444") || cpfApenasNumeros.equals("55555555555") ||
                cpfApenasNumeros.equals("66666666666") || cpfApenasNumeros.equals("77777777777") ||
                cpfApenasNumeros.equals("88888888888") || cpfApenasNumeros.equals("99999999999") ||
                (cpfApenasNumeros.length() != 11))
            throw new CpfInvalidoException("Cpf Invalido");

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpfApenasNumeros.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpfApenasNumeros.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpfApenasNumeros.charAt(9)) && (dig11 == cpfApenasNumeros.charAt(10))) {
                return (true);
            }
            else
                throw new CpfInvalidoException("Cpf Invalido");
        } catch (Exception erro) {
            throw new CpfInvalidoException("Cpf Invalido");
        }
    }

    private static boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        }catch (AddressException exception){
            throw new EmailInvalidoException("Email Invalido");
        }
    }

    private static boolean possui2ContatosOuMais(int qualtidadeDeContatos){
        if(qualtidadeDeContatos<2)
            throw new PoucosContatosExcepption();
        return true;
    }
}
