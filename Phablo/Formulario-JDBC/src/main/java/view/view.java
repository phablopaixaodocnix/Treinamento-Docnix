package view;

import controller.ContatosController;
import controller.EnderecosController;
import controller.FormulariosController;
import funcoesAuxiliares.FuncoesAuxiliares;
import model.Formulario;

import java.util.ArrayList;
import java.util.Scanner;

import static funcoesAuxiliares.FuncoesAuxiliares.mostrarATabela_Editar_Excluir;
import static funcoesAuxiliares.FuncoesAuxiliares.preencherEEnviarUmFormularioParaOBanco;

public class view {
    public static void main(String[] args) {
        ArrayList<Formulario> formularios;
        FormulariosController formulariosController = new FormulariosController();
        EnderecosController enderecosController = new EnderecosController();
        ContatosController contatosController = new ContatosController();
        formularios = FuncoesAuxiliares.retornarFormularioComOsDadosExistentesNoBanco(formulariosController, enderecosController, contatosController);
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            System.out.printf("\n\n");
            System.out.println("-----------------------------------Desafio Formulário em Java-----------------------------------");

            do {
                System.out.printf(" O que deseja fazer?  (1)Preencher e enviar um formulário  (2) Mostrar a tabela		");
                opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                switch (opcao) {
                    case 1:
                        preencherEEnviarUmFormularioParaOBanco(formularios, formulariosController, enderecosController, contatosController, scanner);
                        break;


                    case 2:
                        mostrarATabela_Editar_Excluir(formularios, formulariosController, enderecosController, contatosController, scanner);
                        break;


                    default:
                        System.out.println(" Opcao Invalida");
                        opcao = 1;
                }
            } while (opcao == 1 || opcao == 2);

        }
    }




}
