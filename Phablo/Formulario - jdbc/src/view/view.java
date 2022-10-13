package view;

import controller.ContatosController;
import controller.EndereçosController;
import controller.FormulariosController;
import funçõesAuxiliares.FunçõesAuxiliares;
import model.Formulario;

import java.util.ArrayList;
import java.util.Scanner;

public class view {
    public static void main(String[] args) {
        ArrayList<Formulario> formularios;
        FormulariosController formulariosController = new FormulariosController();
        EndereçosController endereçosController = new EndereçosController();
        ContatosController contatosController = new ContatosController();
        formularios = FunçõesAuxiliares.puxarOsDadosDoBancoParaOFormularioLocal(formulariosController, endereçosController, contatosController);
        try (Scanner scanner = new Scanner(System.in)) {
            int opção = 0;
            //formularios.get(0).imprimirFormulario();
            //System.out.println(formularios.get(0).getContatos().get(0).getIdFormulario()+" "+formularios.get(0).getContatos().get(0).getNome()+" "+formularios.get(0).getContatos().get(0).getEmail()+" "+formularios.get(0).getContatos().get(0).getTelefone());
            //System.out.println(formularios.get(0).getContatos().get(1).getIdFormulario()+" "+formularios.get(0).getContatos().get(1).getNome()+" "+formularios.get(0).getContatos().get(1).getEmail()+" "+formularios.get(0).getContatos().get(1).getTelefone());

            System.out.printf("\n\n");
            System.out.println("-----------------------------------Desafio Formulário em Java-----------------------------------");

            do {
                System.out.printf(" O que deseja fazer?  (1)Preencher e enviar um formulário  (2) Mostrar a tabela		");
                opção = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                switch (opção) {
                    case 1: {
                        //formulário preenchido no java
                        Formulario formulario = FunçõesAuxiliares.preencherFormularioERetornalo(scanner);

                        //formulario salvo no banco de dados e seu id é definido pelo retorno da função salvar para que fique de acordo com o que está no banco
                        formulario.setId(formulariosController.salvar(formulario));

                        //salvando o endereço e os contatos no banco
                        endereçosController.salvar(formulario.getEndereço(), formulario.getId());
                        for (int i = 0; i < formulario.getContatos().size(); i++) {
                            formulario.getContatos().get(i).setId(contatosController.salvar(formulario.getContatos().get(i), formulario.getId()));
                        }

                        //arrumando os id's do endereço e dos contatos para que fique de acordo com o banco
                        formulario.getEndereço().setIdFormulario(formulario.getId());
                        for (int i = 0; i < formulario.getContatos().size(); i++) {
                            formulario.getContatos().get(i).setIdFormulario(formulario.getId());
                        }
                        formularios.add(formulario);
                        break;
                    }

                    case 2: {
                        FunçõesAuxiliares.manipularATabela(formularios, formulariosController, endereçosController, contatosController, scanner);
                        break;
                    }

                    default:
                        System.out.println(" Opção Invalida");
                        opção = 1;
                }
            } while (opção == 1 || opção == 2);

        }
    }
}
