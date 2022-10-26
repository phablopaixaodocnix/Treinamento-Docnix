package FuncoesAuxiliares;

import controller.ContatosController;
import controller.EnderecoController;
import controller.FormularioController;
import model.Contato;
import model.Endereco;
import model.Formulario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class FuncoesAuxiliares {

    public static boolean isCPF(String CPF) {

        if (CPF.length() != 11)
            return false;

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static Formulario preencherFormularioERetornalo(Scanner scanner) {
        String nome, email, cpf = "11111111111", cidade, bairro, rua, uf,cep, escolaridade = "";
        int quadra, casa,  lote, numero, opçãoContatos = 0;
        List<Contato> contatos = new ArrayList<>();
        System.out
                .printf(" Preenchendo formulário\n\n\tDados Pessoais");

        System.out.printf("\n  Nome: ");
        nome = scanner.nextLine();

        System.out.printf("  Email: ");
        email = scanner.nextLine();

        boolean isValid = false;
        do {
            System.out.printf("  Cpf(Apenas números): ");
            cpf = scanner.nextLine();
            isValid = FuncoesAuxiliares.isCPF(cpf);
            if (cpf.length() != 11) {
                System.out.println("   O cpf deve conter exatamente 11 dígitos");
            } else if (!isValid)
                System.out.println("   Cpf Invalido!!");
        } while (!isValid || cpf.length() != 11);

        boolean condição = false;
        do {
            System.out.printf("  Escolaridade:  (1) Ensino Fundamental  (2) Ensino Médio  (3) Ensino Superior    ");
            int a = scanner.nextInt();
            scanner.nextLine();
            if (a == 1)
                escolaridade = "Ensino Fundamental";
            else if (a == 2)
                escolaridade = "Ensino Médio";
            else if (a == 3)
                escolaridade = "Ensino Superior";
            condição = (((a == 1) || (a == 2)) || (a == 3));
        } while (!condição);

        System.out.printf("\n\tEndereço\n  UF: ");
        uf = scanner.nextLine();

        System.out.printf("  Cidade: ");
        cidade = scanner.nextLine();

        System.out.printf("  Bairro: ");
        bairro = scanner.nextLine();

        System.out.printf("  Rua: ");
        rua = scanner.nextLine();

        System.out.printf("  Quadra: ");
        quadra = scanner.nextInt();

        System.out.printf("  Casa: ");
        casa = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("  Cep(apenas números): ");
        cep = scanner.nextLine();

        System.out.printf("  Lote: ");
        lote = scanner.nextInt();

        System.out.printf("  Numero: ");
        numero = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("\n\tContatos\n");
        for (int i = 0; opçãoContatos != 2; i++) {
            String nomeContato, telefoneContato, emailContato;
            System.out.println("   Contato " + (i + 1));
            System.out.printf("  Nome: ");
            nomeContato = scanner.nextLine();
            System.out.printf("  Telefone: ");
            telefoneContato = scanner.nextLine();
            System.out.printf("  Email: ");
            emailContato = scanner.nextLine();
            if (i >= 1) {
                do {
                    System.out.printf("   Mais um contato?  (1) Sim  (2) Não  ");
                    opçãoContatos = scanner.nextInt();
                    scanner.nextLine();
                    if (opçãoContatos != 1 && opçãoContatos != 2)
                        System.out.printf("   Opção inválida");
                } while (opçãoContatos != 1 && opçãoContatos != 2);
            }
            Contato contatoASerAdicionado = new Contato(nomeContato, telefoneContato, emailContato);
            contatos.add(contatoASerAdicionado);
        }
        System.out.println("   Formulário enviado com sucesso!!\n");

        Formulario formularioPreenchido = new Formulario(nome, email, cpf, new Endereco(cidade, bairro, rua, quadra, casa, cep, lote, numero, uf),
                escolaridade, contatos);
        formularioPreenchido.getEndereço().setFormulario(formularioPreenchido);
        for (int i = 0; i < formularioPreenchido.getContatos().size(); i++) {
            formularioPreenchido.getContatos().get(i).setFormulario(formularioPreenchido);
        }
        return formularioPreenchido;
    }

    public static void construirATabela(List<Formulario> formularios) {
        System.out.println();
        for (int i = 0; i < formularios.size(); i++)
            System.out.println("  Formulário " + (i + 1) + "\tNome: " + formularios.get(i).getNome() + "    Email: "
                    + formularios.get(i).getEmail() + "\tEscolaridade: " + formularios.get(i).getEscolaridade());
        System.out.println();
    }

    private static void excluirFormularioDaTabela(List<Formulario> formularios,FormularioController formularioController, Scanner scanner,
                                                 Boolean excluirOuEditar) {

        int formularioASerExcluído = 0;
        if (!podeExcluirDaTabela(formularios)) {
            System.out.println("  Tabela vazia!");
        } else {
            do {
                String k;
                if (excluirOuEditar)
                    k = "excluir";
                else
                    k = "editar";
                System.out.println("  Qual formulario deseja " + k + "? (-1 para voltar ao menu anterior)");
                formularioASerExcluído = scanner.nextInt();
                scanner.nextLine();
                if (formularioASerExcluído < 1 || formularioASerExcluído > formularios.size()) {
                    if (formularioASerExcluído != -1)
                        System.out.println("  Formulário Inexistente");
                    else
                        break;
                }
            } while (formularioASerExcluído < 1 || formularioASerExcluído > formularios.size());
            if (formularioASerExcluído != -1) {
                formularioController.deletarFormulario(formularios.get(formularioASerExcluído-1));
                formularios.remove(formularioASerExcluído - 1);
            }
        }

    }

    public static void manipularATabela(List<Formulario> formularios,FormularioController formularioController, Scanner scanner) {
        if (!podeExcluirDaTabela(formularios)) {
            System.out.println("  Tabela vazia! Nenhum formulário foi preenchido.");
        } else {

            int opção;
            do {
                construirATabela(formularios);
                System.out.println(
                        "  O que deseja fazer?  (1) Excluir formulario da tabela  (2) Editar formulario  (3) Mostrar todos os dados de um formulário  (4) Voltar ao menu anterior  ");
                opção = scanner.nextInt();
                scanner.nextLine();

                switch (opção) {
                    case 1:
                        excluirFormularioDaTabela(formularios,formularioController, scanner, true);
                        break;

                    case 2:
                        edirtarFormularioDaTabela(formularios,formularioController, scanner);
                        break;

                    case 3:
                        mostrarTodosDadosDeUmFormulario(formularios, scanner);
                        break;

                    case 4:
                        break;

                    default:
                        System.out.println(" Opção Invalida");
                }
            } while (opção < 1 || opção > 4);

        }
    }

    private static void mostrarTodosDadosDeUmFormulario(List<Formulario> formularios, Scanner scanner) {
        int i = 0;
        do {
            System.out.println(" Qual formulário deseja mostrar? ");
            i = scanner.nextInt();
            if (i < 1 || i > formularios.size())
                System.out.println("  Formulário Inexistente!!");
        } while (i < 1 || i > formularios.size());
        formularios.get(i - 1).imprimirFormulario();
    }

    private static void edirtarFormularioDaTabela(List<Formulario> formularios,FormularioController formularioController, Scanner scanner) {
        int formularioASerEditado = 0;
        do {
            System.out.println("  Qual formulário deseja editar? ");
            formularioASerEditado = scanner.nextInt();
            scanner.nextLine();
            if (formularioASerEditado < 1 || formularioASerEditado > formularios.size())
                System.out.println("  Formulário Inexistente");
        } while (formularioASerEditado < 1 || formularioASerEditado > formularios.size());

        formularios.get(formularioASerEditado - 1).imprimirFormulario();
        int opção = 0, opçãoEndereço = 0, opçãoContatos = 0;
        do {
            System.out.println("\n  O que deseja editar? ");
            System.out.println("  (1) Nome");
            System.out.println("  (2) Email");
            System.out.println("  (3) Cpf");
            System.out.println("  (4) Endereço");
            System.out.println("  (5) Contatos");
            System.out.println("  (6) Voltar ao menu anterior");
            System.out.println("  Informe a opção desejada: ");
            opção = scanner.nextInt();
            scanner.nextLine();

            switch (opção) {
                case 1:
                    System.out.println("  Informe o novo nome: ");
                    formularios.get(formularioASerEditado - 1).setNome(scanner.nextLine());
                    System.out.println("  Nome editado com sucesso!!");
                    break;

                case 2:
                    System.out.println("  Informe o novo email: ");
                    formularios.get(formularioASerEditado - 1).setEmail(scanner.nextLine());
                    System.out.println("  Email editado com sucesso!!");
                    break;

                case 3:
                    boolean écpf = true;
                    do {
                        System.out.println("  Informe o novo Cpf: ");
                        String novoCpf = scanner.nextLine();
                        écpf = isCPF(novoCpf);
                        if (écpf) {
                            formularios.get(formularioASerEditado - 1).setCpf(novoCpf);
                            System.out.println("  Cpf editado com sucesso!!");
                        } else {
                            System.out.println(" Cpf Inválido");
                        }
                    } while (!écpf);
                    break;

                case 4:
                    do {
                        System.out.println("   (1) cidade");
                        System.out.println("   (2) bairro");
                        System.out.println("   (3) rua");
                        System.out.println("   (4) quadra");
                        System.out.println("   (5) casa");
                        System.out.println("   (6) cep");
                        System.out.println("   (7) lote");
                        System.out.println("   (8) numero");
                        System.out.println("   (9) uf");
                        System.out.println("   Informe a opção desejada: ");
                        opçãoEndereço = scanner.nextInt();
                        scanner.nextLine();

                        switch (opçãoEndereço) {
                            case 1:
                                System.out.println("  Informe a nova cidade: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setCidade(scanner.nextLine());
                                System.out.println("  Cidade editado com sucesso!!");
                                break;
                            case 2:
                                System.out.println("  Informe o novo bairro: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setBairro(scanner.nextLine());
                                System.out.println("  Bairro editado com sucesso!!");
                                break;
                            case 3:
                                System.out.println("  Informe a nova rua: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setRua(scanner.nextLine());
                                System.out.println("  Rua editado com sucesso!!");
                                break;
                            case 4:
                                System.out.println("  Informe a nova quadra: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setQuadra(scanner.nextInt());
                                scanner.nextLine();
                                System.out.println("  Quadra editado com sucesso!!");
                                break;
                            case 5:
                                System.out.println("  Informe a nova casa: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setCasa(scanner.nextInt());
                                scanner.nextInt();
                                System.out.println("  Casa editado com sucesso!!");
                                break;
                            case 6:
                                System.out.println("  Informe o novo cep: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setCep(scanner.nextLine());
                                System.out.println("  Cep editado com sucesso!!");
                                break;
                            case 7:
                                System.out.println("  Informe o novo lote: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setLote(scanner.nextInt());
                                scanner.nextLine();
                                System.out.println("  Lote editado com sucesso!!");
                                break;
                            case 8:
                                System.out.println("  Informe o novo numero: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setNumero(scanner.nextInt());
                                scanner.nextLine();
                                System.out.println("  Numero editado com sucesso!!");
                                break;
                            case 9:
                                System.out.println("  Informe o novo estado: ");
                                formularios.get(formularioASerEditado - 1).getEndereço().setUf(scanner.nextLine());
                                System.out.println("  Numero editado com sucesso!!");
                                break;
                            default:
                                System.out.println("  Opção invalida!!");
                        }////
                    } while (opçãoEndereço < 1 || opçãoEndereço > 9);
                    break;

                case 5:

                    do {
                        System.out.println("  Qual contato deseja editar?");
                        opçãoContatos = scanner.nextInt();
                        scanner.nextLine();
                        if (opçãoContatos < 1 || opçãoContatos > formularios.get(formularioASerEditado - 1).getContatos().size()) {
                            System.out.println("  Contato Inválido");
                            break;
                        } else {
                            System.out.println("  Informe o novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.println("  Informe o novo e-mail: ");
                            String email = scanner.nextLine();
                            System.out.println("  Informe o novo telefone: ");
                            String telefone = scanner.nextLine();
                            formularios.get(formularioASerEditado - 1).getContatos().set(opçãoContatos - 1, new Contato(nome, telefone, email));
                        }
                    } while (opçãoContatos < 1 || opçãoContatos > formularios.get(formularioASerEditado - 1).getContatos().size());

                    break;

                case 6:
                    break;

                default:
                    System.out.println("	Opção Inválida");
            }

            if (opção == 6)
                break;

        } while (opção < 1 || opção > 6);

        formularios.get(formularioASerEditado - 1).getContatos().get(0).setFormulario(formularios.get(formularioASerEditado - 1));
        formularioController.atualizarFormulario(formularios.get(formularioASerEditado - 1));
    }

    private static boolean podeExcluirDaTabela(List<Formulario> formularios) {
        if (formularios.size() <= 0)
            return false;
        return true;
    }

    public static List<Formulario> recuperarOsDadosDoBanco(FormularioController formularioController, EnderecoController enderecoController, ContatosController contatosController){
        List<Formulario> formularios = formularioController.listarFormularios();

        for(int i = 0 ; i < formularios.size(); i++){
            Formulario formularioParaArrumarOEndereçoEOsContatos = formularios.get(i);

            Endereco endereco = enderecoController.listarOEnderecoDeUmFormulario(formularioParaArrumarOEndereçoEOsContatos).get(0);
            formularioParaArrumarOEndereçoEOsContatos.setEndereço(endereco);

            List<Contato> contatos = contatosController.listarContatosDeUmFormulario(formularioParaArrumarOEndereçoEOsContatos);
            formularioParaArrumarOEndereçoEOsContatos.setContatos(contatos);

            formularios.set(i,formularioParaArrumarOEndereçoEOsContatos);
        }
        return formularios;
    }

    public static void mostrarATabela(List<Formulario> formularios,FormularioController formularioController,  Scanner scanner) {
        FuncoesAuxiliares.manipularATabela(formularios, formularioController, scanner);
    }

    public static void preencherEEnviarUmFormulario(FormularioController formularioController, List<Formulario> formularios, Scanner scanner) {
        Formulario f = FuncoesAuxiliares.preencherFormularioERetornalo(scanner);
        formularios.add(f);
        formularioController.cadastrarFormulario(formularios.get(formularios.size() - 1));
    }

}

