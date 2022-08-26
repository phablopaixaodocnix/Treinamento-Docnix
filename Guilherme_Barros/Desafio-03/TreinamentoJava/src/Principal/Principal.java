package Principal;

import Cadastro.Cadastro;
import Cadastro.Contato;
import Controller.ControllerCadastros;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private Scanner scan = new Scanner(System.in);
    private Input input = new Input();
    public ArrayList<Cadastro> cadastroArray = new ArrayList();
    public ArrayList<Contato> contatoArray = new ArrayList();
    ControllerCadastros controllerCadastros = new ControllerCadastros();
    Cadastro cadastro;
    Contato contato;

    private int checkNumberAux = 0;
    private int calc = 0;
    private String stringRegex = "[0-9]*";

    private int EdicaoAux = 0;
    private String Emailaux;
    private int validarEmailAux = 0;
    private int contadorAux = 1;
    private String StringAux;

    private String opcaoAdicionarContatos;
    private String opcao;

    private String ID = "0";
    private String IDOpcao;
    private String validarIdSelecionado;


    public void MenuDoCrud() {
        do {
            System.out.println(printMenu());
            opcao = checkNumberOpcao();
            switch (opcao) {
                case "1":
                    //Incluir Cadastros
                    incluirArray();
                    break;
                case "2":
                    //Editar Cadastros
                    editarArray();
                    break;
                case "3":
                    //Excluir Cadastro
                    excluirArray();
                    break;
                case "4":
                    //Listar Cadastro
                    if (cadastroArray.isEmpty()) {
                        System.out.println("Cadastre Primeiramente \n");
                    } else {
                        controllerCadastros.listar(cadastroArray);
                    }
                    break;
                case "5":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Digite um Valor válido");
            }
        } while (!opcao.equals("5"));
    }

    public String checkNumberOpcao() {
        return validarInputNumero("valor");
    }

    public void setDados() {
        System.out.println("Digite seu Nome:");
        input.setNome(scan.next());
        System.out.println("Digite seu Email:");
        input.setEmail(validarEmail());
        System.out.println("Digite seu CPF:");
        input.setCpf(validarCPF());
        System.out.println("Digite seu CEP:");
        input.setCep(validarCEP());
        System.out.println("Digite sua Rua:");
        input.setRua(scan.next());
        System.out.println("Digite seu Cidade:");
        input.setCidade(scan.next());
        System.out.println("Digite seu Nome de Contato Principal:");
        input.setNomeContatoPrincipal(scan.next());
        System.out.println("Digite seu Email de Contato Principal:");
        input.setEmailContatoPrincipal(validarEmail());
        System.out.println("Digite seu Telefone de Contato Principal:");
        input.setTelefoneContatoPrincipal(validarTelefone());
        if (EdicaoAux == 1) {
            contatoArray = new ArrayList<>();
            for (int i = 0; i < cadastroArray.size(); i++) {
                if (IDOpcao.equals(cadastroArray.get(i).getID())) {
                    ArrayList<Contato> arrayContato = cadastroArray.get(i).getArrayListContato();
                    for (int j = 0; j < arrayContato.size(); j++) {
                        System.out.println("Contato Alternativo " + j + "\n");
                        contato = new Contato();
                        System.out.println("Digite o Nome: ");
                        contato.setNomeAlternativo(scan.next());
                        System.out.println("Digite o Email: ");
                        contato.setEmailAlternativo(validarEmail());
                        System.out.println("Digite o Telefone: ");
                        contato.setTelefoneAlternativo(validarTelefone());
                        contatoArray.add(contato);
                    }
                }
            }
            input.setArrayList(contatoArray);
        } else {
            System.out.println("0 - Adicionar Contato Alternativo" + "   " + "1 - Salvar\n");
        opcaoAdicionarContatos = validarIdSelecionado();
            while (opcaoAdicionarContatos.equals("0")) {
                contato = new Contato();
                System.out.println("Digite o Nome: ");
                contato.setNomeAlternativo(scan.next());
                System.out.println("Digite o Email: ");
                contato.setEmailAlternativo(validarEmail());
                System.out.println("Digite o Telefone: ");
                contato.setTelefoneAlternativo(validarTelefone());
                contatoArray.add(contato);
                System.out.println("0 - Adicionar Contato Alternativo" + "   " + "1 - Salvar\n");
                opcaoAdicionarContatos = validarIdSelecionado();
            }
            input.setArrayList(contatoArray);
        }
    }

    public void setCadastro() {
        cadastro = new Cadastro();

        if (EdicaoAux == 1) {
            cadastro.setID(IDOpcao);
            EdicaoAux = 0;
        } else {
            cadastro.setID(ID);
            ID = String.valueOf(Integer.parseInt(ID) + 1);
        }

        cadastro.setNome(input.getNome());
        cadastro.setEmail(input.getEmail());
        cadastro.setCpf(input.getCpf());
        cadastro.setCep(input.getCep());
        cadastro.setRua(input.getRua());
        cadastro.setCidade(input.getCidade());
        cadastro.setNomeContatoPrincipal(input.getNomeContatoPrincipal());
        cadastro.setEmailContatoPrincipal(input.getEmailContatoPrincipal());
        cadastro.setTelefoneContatoPrincipal(input.getTelefoneContatoPrincipal());
        cadastro.setArrayListContato(input.getArrayList());
        contatoArray = new ArrayList();
    }

    public String validarInputNumero(String campoDeVerificacao) {
        String campoInput = scan.next();
        while (!campoInput.matches(stringRegex)) {
            System.out.println("Digite um " + campoDeVerificacao + " válido");
            campoInput = scan.next();
        }
        return campoInput;
    }

    public String validarIdSelecionado() {
        validarIdSelecionado = validarInputNumero("ID");
        while (!validarIdSelecionado.equals("0") && !validarIdSelecionado.equals("1")){
            System.out.println("Digite um ID válido");
            validarIdSelecionado = validarInputNumero("ID");
        }
        return validarIdSelecionado;
    }

    public String validarCampoCPF() {
        StringAux = validarInputNumero("CPF");
        calc = 0;
        contadorAux = 1;
        return StringAux;
    }

    public String validarCPF() {
        validarCampoCPF();
        while (StringAux.length() != 11) {
            System.out.println("Digite um CPF válido");
            validarCampoCPF();
        }
        for (int i = 0; i < 9; i++) {
            calc += charToInt(i) * contadorAux;
            contadorAux++;
        }

        calc = calc % 11;
        // Se calc for igual a primeiro digito verificador, primeiro digito valido
        if (calc >= 10) calc = 0;
        if (calc ==

                charToInt(9)) {
            //Calculando Segundo Digito Verificador
            contadorAux = 0;
            calc = 0;
            for (int i = 0; i < 10; i++) {
                calc += charToInt(i) * contadorAux;
                contadorAux++;
            }
            calc = calc % 11;
            if (calc == charToInt(10)) {
                return StringAux;
            } else {
                validarInputNumero("CPF");
            }
        } else {
            validarInputNumero("CPF");
        }
        return null;
    }

    public String validarCEP() {
        StringAux = validarInputNumero("CEP");
        while (StringAux.length() != 8) {
            System.out.println("Digite um CEP válido");
            StringAux = validarInputNumero("CEP");
        }
        return StringAux;
    }

    public String validarEmail() {
        Emailaux = scan.next();
        do {
            validarEmailAux = 0;
            if (Emailaux.contains("@") && Emailaux.contains(".")) {
                if (Emailaux.indexOf("@") < Emailaux.indexOf(".")) {
                    validarEmailAux = 1;
                    return Emailaux;
                }
            } else {
                System.out.println("Digite um Email Válido");
                Emailaux = scan.next();
            }
        } while (validarEmailAux != 1);
        return Emailaux;
    }

    public String validarTelefone() {
        StringAux = validarInputNumero("Número");
        while (StringAux.length() != 11) {
            System.out.println("Digite um Numero de celular válido - Exemplo - 62912345678");
            StringAux = validarInputNumero("Número");
        }
        return StringAux;
    }

    public String printMenu() {
        return "****** Escolha uma Opção ******\n" +
                "| 1 - Inserir novo Cadastro   |\n" +
                "| 2 - Editar Cadastro         |\n" +
                "| 3 - Excluir Cadastro        |\n" +
                "| 4 - Listar Cadastros        |\n" +
                "| 5 - Finalizar Programa      |\n" +
                "*******************************\n";
    }

    public void editarArray() {
        if (cadastroArray.isEmpty()) {
            System.out.println("Cadastre Primeiramente \n");
        } else {
            controllerCadastros.listar(cadastroArray);
            System.out.println("****** Selecione o ID de Edição: ******");
            IDOpcao = validarIdSelecionado();
            EdicaoAux = 1;
            setDados();
            setCadastro();
            controllerCadastros.editar(IDOpcao, cadastroArray, cadastro);
            for (int i = 0; i < cadastroArray.size(); i++) {
                System.out.println(cadastroArray.get(i).toString());
            }
        }
    }

    public void incluirArray() {
        setDados();
        setCadastro();
        controllerCadastros.incluir(cadastro, cadastroArray);
    }

    public void excluirArray() {
        if (cadastroArray.isEmpty()) {
            System.out.println("Cadastre Primeiramente \n");
        } else {
            controllerCadastros.listar(cadastroArray);
            System.out.println("****** Selecione o ID que deseja Excluir ******");
            IDOpcao = validarIdSelecionado();
            controllerCadastros.excluir(IDOpcao, cadastroArray);
            ID = String.valueOf(cadastroArray.size());
        }
    }

    public int charToInt(int Valor) {
        return Character.getNumericValue(StringAux.charAt(Valor));
    }
}
