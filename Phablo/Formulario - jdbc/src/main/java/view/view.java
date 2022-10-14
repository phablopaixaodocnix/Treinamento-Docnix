package view;

import controller.ContatosController;
import controller.EndereçosController;
import controller.FormulariosController;
import funçõesAuxiliares.FunçõesAuxiliares;
import model.Formulario;

import java.util.ArrayList;
import java.util.Scanner;

import static funçõesAuxiliares.FunçõesAuxiliares.mostrarATabela_Editar_Excluir;
import static funçõesAuxiliares.FunçõesAuxiliares.preencherEEnviarUmFormularioParaOBanco;

public class view {
    public static void main(String[] args) {
        ArrayList<Formulario> formularios;
        FormulariosController formulariosController = new FormulariosController();
        EndereçosController endereçosController = new EndereçosController();
        ContatosController contatosController = new ContatosController();
        formularios = FunçõesAuxiliares.retornarFormularioComOsDadosExistentesNoBanco(formulariosController, endereçosController, contatosController);
        try (Scanner scanner = new Scanner(System.in)) {
            int opção = 0;
            System.out.printf("\n\n");
            System.out.println("-----------------------------------Desafio Formulário em Java-----------------------------------");

            do {
                System.out.printf(" O que deseja fazer?  (1)Preencher e enviar um formulário  (2) Mostrar a tabela		");
                opção = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                switch (opção) {
                    case 1:
                        preencherEEnviarUmFormularioParaOBanco(formularios, formulariosController, endereçosController, contatosController, scanner);
                        break;


                    case 2:
                        mostrarATabela_Editar_Excluir(formularios, formulariosController, endereçosController, contatosController, scanner);
                        break;


                    default:
                        System.out.println(" Opção Invalida");
                        opção = 1;
                }
            } while (opção == 1 || opção == 2);

        }
    }




}
