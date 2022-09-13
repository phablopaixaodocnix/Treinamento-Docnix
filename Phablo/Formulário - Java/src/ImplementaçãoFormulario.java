import java.util.ArrayList;
import java.util.Scanner;
import FunçõesAuxiliares.FunçõesAuxiliares;

public class ImplementaçãoFormulario {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Formulario> formularios = new ArrayList<Formulario>();
		
		int opção=0;

		System.out.println();
		System.out.println();
		System.out.println("-----------------------------------Desafio Formulário em Java-----------------------------------");

		do{
			System.out.printf(" O que deseja fazer?  (1)Preencher um formulário  (2) Mostrar a tabela		");
			opção = scanner.nextInt();
			System.out.println();

			switch(opção){
				case 1:{
					String nome;
					String email;
					String cpf = "11111111111";
					int escolaridade;
					System.out.printf(" Preenchendo formulário "+ (Formulario.quantidadeTotalDeFormularios + 1));

					System.out.printf("\n  Nome: ");
					nome = scanner.next();

					System.out.printf("  Email: ");
					email = scanner.next();

					boolean isValid = false;
					do{
						System.out.printf("  Cpf(Apenas números): ");
						cpf = scanner.next();
						isValid = FunçõesAuxiliares.isCPF(cpf);
						if(!isValid)
							System.out.println("   Cpf Invalido!!");
					}while(!isValid);

					boolean condição = false;
					do{
						System.out.printf("  Escolaridade:  (1) Ensino Fundamental  (2) Ensino Médio  (3) Ensino Superior    ");
						escolaridade = scanner.nextInt();
						condição = escolaridade != 1 || escolaridade != 2 || escolaridade != 3;
					}while(condição);

				break;
			}
				case 2:
				break;
				default:
				System.out.println(" Opção Invalida");
			}
		}while(opção != 1 || opção != 2);
		
	}
}
