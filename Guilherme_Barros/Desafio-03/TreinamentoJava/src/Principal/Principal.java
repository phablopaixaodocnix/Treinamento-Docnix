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

    private long checkNumberAux = 0;
    private int calc = 0;

    private int EdicaoAux = 0;
    private long CPFaux = 0;
    private long CEPaux = 0;
    private String Emailaux;
    private int validarEmailAux = 0;
    private long telefoneAux = 0;
    private int contadorAux = 1;
    private String StringAux;

    private long opcaoAdicionarContatos = 0;
    private int opcao = 0;

    private int ID;
    private long IDOpcao = 0;
    private long validarIdSelecionado = 0;


    public void MenuDoCrud() {
        do {
            System.out.println(printMenu());
            opcao = checkNumberOpcao();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    //Incluir Cadastros
                    incluirArray();
                    break;
                case 2:
                    //Editar Cadastros
                    editarArray();
                    break;
                case 3:
                    //Excluir Cadastro
                    excluirArray();
                    break;
                case 4:
                    //Listar Cadastro
                    controllerCadastros.listar(cadastroArray);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Digite uma Opção Válida");
            }
        } while (opcao != 5);
    }

    public int checkNumberOpcao() {
        if (scan.hasNextInt()) {
            return scan.nextInt();
        } else {
            return 0;
        }

    }

    public void setDados() {
        System.out.println("Digite seu Nome:");
        input.setNome(scan.next());
//        System.out.println("Digite seu Email:");
//        input.setEmail(validarEmail());
//        System.out.println("Digite seu CPF:");
//        input.setCpf(validarCampoCPF());
//        System.out.println("Digite seu CEP:");
//        input.setCep(validarCEP());
//        System.out.println("Digite sua Rua:");
//        input.setRua(scan.next());
//        System.out.println("Digite seu Cidade:");
//        input.setCidade(scan.next());
//        System.out.println("Digite seu Nome de Contato Principal:");
//        input.setNomeContatoPrincipal(scan.next());
//        System.out.println("Digite seu Email de Contato Principal:");
//        input.setEmailContatoPrincipal(validarEmail());
//        System.out.println("Digite seu Telefone de Contato Principal:");
//        input.setTelefoneContatoPrincipal(validarTelefone());
        System.out.println("0 - Adicionar Contato Alternativo" +"   "+ "1 - Salvar\n");
        opcaoAdicionarContatos = validarOpcaoSelecionadaDeContatoAlternativo(validarIdSelecionado());
        while (opcaoAdicionarContatos == 0) {
            contato = new Contato();
            contato.setIdContato(ID);
            System.out.println("Digite seu Nome:");
            contato.setNomeAlternativo(scan.next());
//            System.out.println("Digite seu Email:");
//            contato.setEmailAlternativo(validarEmail());
//            System.out.println("Digite seu Telefone:");
//            contato.setTelefoneAlternativo(validarTelefone());
            contatoArray.add(contato);
            System.out.println("0 - Adicionar Contato Alternativo" +"   "+ "1 - Salvar\n");
            opcaoAdicionarContatos = validarOpcaoSelecionadaDeContatoAlternativo(validarIdSelecionado());
        }
        input.setArrayList(contatoArray);
        }

    public void setCadastro() {
        cadastro = new Cadastro();

        if (EdicaoAux == 1) {
            cadastro.setID(IDOpcao);
            EdicaoAux = 0;
        } else {
            cadastro.setID(ID);

            ID++;
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

    public long validarNumero() {
        if (scan.hasNextLong()) {
            checkNumberAux = scan.nextLong();
            scan.nextLine();
            return checkNumberAux;
        } else {
            scan.nextLine();
            return -1;
        }
    }

    public long validarIdSelecionado() {
        validarIdSelecionado = validarNumero();
        while (validarIdSelecionado == -1) {
            System.out.println("Digite um Numero Válido");
            validarIdSelecionado = validarNumero();
        }
        return validarIdSelecionado;
    }

    public long validarOpcaoSelecionadaDeContatoAlternativo(long op) {
        while (op != 1 && op != 0) {
            System.out.println("Digite uma Opção Válida");
            op = validarNumero();
        }
        return op;
    }

    public long validarCampoCPF() {
        CPFaux = validarNumero();
        calc = 0;
        contadorAux = 1;
        while (CPFaux == -1) {
            System.out.println("Digite um CPF Válido");
            CPFaux = validarNumero();
        }
        return validarCPF();

    }

    public long validarCPF() {
        StringAux = Long.toString(CPFaux);

        if (StringAux.length() != 11) {
            System.out.println("Digite um CPF Válido");
            validarCampoCPF();
        } else {
            for (int i = 0; i < 9; i++) {
                calc += charToInt(i) * contadorAux;
                contadorAux++;
            }
            calc = calc % 11;
            // Se calc for igual a primeiro digito verificador, primeiro digito valido
            if (calc >= 10) calc = 0;
            if (calc == charToInt(9)) {
                //Calculando Segundo Digito Verificador
                contadorAux = 0;
                calc = 0;
                for (int i = 0; i < 10; i++) {
                    calc += charToInt(i) * contadorAux;
                    contadorAux++;
                }
                calc = calc % 11;
                if (calc == charToInt(10)) {
                    return CPFaux;
                } else {
                    do {
                        System.out.println("Digite um CPF Válido");
                    } while (validarCampoCPF() != -1);
                }
            } else {
                do {
                    System.out.println("Digite um CPF Válido");
                } while (validarCampoCPF() != -1);

            }
        }
        return -1;
    }

    public long validarCEP() {
        CEPaux = validarNumero();
        while (CEPaux == -1 || String.valueOf(CEPaux).length() != 8) {
            System.out.println("Digite um CEP Válido");
            CEPaux = validarNumero();
        }
        return CEPaux;
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

    public long validarTelefone() {
        telefoneAux = validarNumero();

        while (telefoneAux == -1 || String.valueOf(telefoneAux).length() != 11) {
            System.out.println("Digite um Numero Válido - Exemplo - 62912345678");
            telefoneAux = validarNumero();
            System.out.println(telefoneAux);
        }
        return telefoneAux;
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
            System.out.println("Cadastre Primeiramente");
        } else {
            controllerCadastros.listar(cadastroArray);
            System.out.println("****** Selecione o ID de Edição: ******");
            IDOpcao = validarIdSelecionado();
            setDados();
            EdicaoAux = 1;
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
        System.out.println("****** Selecione o ID que deseja Excluir ******");
        controllerCadastros.listar(cadastroArray);
        IDOpcao = validarIdSelecionado();
        controllerCadastros.excluir(IDOpcao, cadastroArray);
        ID = cadastroArray.size();
    }

    public int charToInt(int Valor) {
        return Character.getNumericValue(StringAux.charAt(Valor));
    }
}
