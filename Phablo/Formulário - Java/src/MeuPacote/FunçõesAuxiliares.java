package MeuPacote;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class FunçõesAuxiliares {
	
	public static boolean isCPF(String CPF) {
				
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
				CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

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
            num = (int)(CPF.charAt(i) - 48);
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
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
							}
        }

  public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

  public static Formulario preencherFormularioERetornalo(Scanner scanner){
					String nome, email ,cpf = "11111111111",cidade,bairro,rua,uf,escolaridade = "";
					int quadra,casa,cep,lote,numero,opçãoContatos=0;
					ArrayList<Contato> contatos = new ArrayList<Contato>();
					System.out.printf(" Preenchendo formulário "+ (Formulario.quantidadeTotalDeFormularios + 1)+"\n\n\tDados Pessoais");

					System.out.printf("\n  Nome: ");
					nome = scanner.nextLine();

					System.out.printf("  Email: ");
					email = scanner.nextLine();

					boolean isValid = false;
					do{
						System.out.printf("  Cpf(Apenas números): ");
						cpf = scanner.nextLine();
						isValid = FunçõesAuxiliares.isCPF(cpf);
						if(cpf.length() != 11){
							System.out.println("   O cpf deve conter exatamente 11 dígitos");
						}
						else if(!isValid)
							System.out.println("   Cpf Invalido!!");
					}while(!isValid || cpf.length() != 11);

					boolean condição = false;
					do{
						System.out.printf("  Escolaridade:  (1) Ensino Fundamental  (2) Ensino Médio  (3) Ensino Superior    ");
						int a = scanner.nextInt();
						scanner.nextLine();
						if(a == 1) escolaridade = "Ensino Fundamental";
						else if(a == 2) escolaridade = "Ensino Médio";
						else if(a == 3) escolaridade = "Ensino Superior";
						condição = (((a == 1) || (a == 2)) || (a == 3));
					}while(!condição);

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

					System.out.printf("  Cep(apenas números): ");
					cep = scanner.nextInt();

					System.out.printf("  Lote: ");
					lote = scanner.nextInt();

					System.out.printf("  Numero: ");
					numero = scanner.nextInt();
					scanner.nextLine();

					System.out.printf("\n\tContatos\n");
					for(int i = 0 ; opçãoContatos != 2; i++){
						String nomeContato, telefoneContato, emailContato;
						System.out.println("   Contato "+ (i+1));
						System.out.printf("  Nome: ");
						nomeContato = scanner.nextLine();
						System.out.printf("  Telefone: ");
						telefoneContato = scanner.nextLine();
						System.out.printf("  Email: ");
						emailContato = scanner.nextLine();
						if(i>=1){
							do{
								System.out.printf("   Mais um contato?  (1) Sim  (2) Não  ");
								opçãoContatos = scanner.nextInt();
								scanner.nextLine();
								if(opçãoContatos != 1 && opçãoContatos != 2) System.out.printf("   Opção inválida");
							}while(opçãoContatos != 1 && opçãoContatos != 2);
						}
						contatos.add(new Contato(nomeContato, telefoneContato, emailContato));
					}
					System.out.println("   Formulário enviado com sucesso!!\n");

					return new Formulario(nome, email, cpf, new Endereço(cidade, bairro, rua, quadra, casa, cep, lote, numero, uf), escolaridade, contatos);
    }

	private static void construirATabela(ArrayList<Formulario> formularios){
		System.out.println();
		for(int i = 0 ; i < formularios.size() ; i++) 
			System.out.println("  Formulário "+(i+1)+"\tNome: "+formularios.get(i).getNome()+"    Email: "+formularios.get(i).getEmail()+"\tEscolaridade: "+formularios.get(i).getEscolaridade());
		System.out.println();
	}

	private static void excluirFormularioDaTabela(ArrayList<Formulario> formularios , int formularioASerExcluído){
		formularios.remove(formularioASerExcluído-1);
	}

	private static boolean podeExcluirDaTabela(ArrayList<Formulario> formularios){
		if(formularios.size()<=0) return false;
		return true;
	}

	public static void manipularATabela(ArrayList<Formulario> formularios ,Scanner scanner){
		if(!podeExcluirDaTabela(formularios)){
			System.out.println("  Tabela vazia! Nenhum formulário foi preenchido.");
		}
		else{

			int opção;
			do{
				construirATabela(formularios);
				System.out.println("  O que deseja fazer?  (1) Excluir formulario da tabela  (2) Editar formulario  (3) Voltar ao menu anterior  ");
				opção = scanner.nextInt(); scanner.nextLine();

				switch(opção){
					case 1:
					if(!podeExcluirDaTabela(formularios)){
						System.out.println("  Tabela vazia!");
					}
					else{
						int formularioASerExcluído;
						do{
							System.out.println("  Qual formulario deseja excluir? (-1 para voltar ao menu anterior)");
							formularioASerExcluído = scanner.nextInt(); scanner.nextLine();
							if(formularioASerExcluído<1 || formularioASerExcluído > formularios.size()){
								if(formularioASerExcluído == -1)
									break;
								else
									System.out.println("  Formulário Inexistente");
							}
						}while(formularioASerExcluído<1 || formularioASerExcluído > formularios.size());
						if(formularioASerExcluído != -1)
							excluirFormularioDaTabela(formularios, formularioASerExcluído);
					}
					break;
				
					case 2:{}
				
					case 3:
					break;

					default:
					System.out.println(" Opção Invalida");
				}
			}while(opção !=3);
		
		}
	}

}
