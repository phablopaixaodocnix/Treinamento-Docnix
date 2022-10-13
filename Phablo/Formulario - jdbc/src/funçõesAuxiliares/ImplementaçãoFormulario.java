package funçõesAuxiliares;

import funçõesAuxiliares.FunçõesAuxiliares;
import model.Formulario;

import java.util.ArrayList;
import java.util.Scanner;

public class ImplementaçãoFormulario {
    public static void main(String[] args) {
        ArrayList<Formulario> formularios = new ArrayList<Formulario>();
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
                        formularios.add(FunçõesAuxiliares.preencherFormularioERetornalo(scanner));
                        break;

                    case 2:
                        //FunçõesAuxiliares.manipularATabela(formularios, scanner);
                        break;

                    default:
                        System.out.println(" Opção Invalida");
                        opção = 1;
                }
            } while (opção == 1 || opção == 2);

        }
    }
}
