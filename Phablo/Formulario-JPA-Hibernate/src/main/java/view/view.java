package view;

import FuncoesAuxiliares.FuncoesAuxiliares;
import controller.ContatosController;
import controller.EnderecoController;
import controller.FormularioController;
import model.Formulario;

import java.util.List;
import java.util.Scanner;

import static FuncoesAuxiliares.FuncoesAuxiliares.mostrarATabela;
import static FuncoesAuxiliares.FuncoesAuxiliares.preencherEEnviarUmFormulario;

public class view {
    public static void main(String[] args) {
        FormularioController formularioController = new FormularioController();
        EnderecoController enderecoController = new EnderecoController();
        ContatosController contatosController = new ContatosController();
        List<Formulario> formularios = FuncoesAuxiliares.recuperarOsDadosDoBanco(formularioController,enderecoController,contatosController);
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
                    case 1: {
                        preencherEEnviarUmFormulario(formularioController, formularios, scanner);
                        break;
                    }

                    case 2: {
                        mostrarATabela(formularios,formularioController,  scanner);
                        break;
                    }

                    default:
                        System.out.println(" Opção Invalida");
                        opção = 1;
                        break;
                }
            } while (opção == 1 || opção == 2);

        }
    }




}
