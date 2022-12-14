package funcoesAuxiliares;

import controller.ContatosController;
import controller.EnderecosController;
import controller.FormulariosController;
import model.Contato;
import model.Endereco;
import model.Formulario;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

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

	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
				CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
	}

	public static Formulario preencherFormularioERetornalo(Scanner scanner) {
		String nome, email, cpf = "11111111111", cidade, bairro, rua, uf, escolaridade = "", cep;
		int quadra, casa, lote, numero, opcaoContatos = 0;
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		System.out
				.printf(" Preenchendo formul??rio " + (Formulario.quantidadeTotalDeFormularios + 1) + "\n\n\tDados Pessoais");

		System.out.printf("\n  Nome: ");
		nome = scanner.nextLine();

		System.out.printf("  Email: ");
		email = scanner.nextLine();

		boolean isValid = false;
		do {
			System.out.printf("  Cpf(Apenas n??meros): ");
			cpf = scanner.nextLine();
			isValid = FuncoesAuxiliares.isCPF(cpf);
			if (cpf.length() != 11) {
				System.out.println("   O cpf deve conter exatamente 11 d??gitos");
			} else if (!isValid)
				System.out.println("   Cpf Invalido!!");
		} while (!isValid || cpf.length() != 11);

		boolean condicao = false;
		do {
			System.out.printf("  Escolaridade:  (1) Ensino Fundamental  (2) Ensino M??dio  (3) Ensino Superior    ");
			int a = scanner.nextInt();
			scanner.nextLine();
			if (a == 1)
				escolaridade = "ensino fundamental";
			else if (a == 2)
				escolaridade = "ensino m??dio";
			else if (a == 3)
				escolaridade = "ensino superior";
			condicao = (((a == 1) || (a == 2)) || (a == 3));
		} while (!condicao);

		System.out.printf("\n\tEndereco\n  UF: ");
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

		System.out.printf("  Cep(apenas n??meros): ");
		cep = scanner.next();

		System.out.printf("  Lote: ");
		lote = scanner.nextInt();

		System.out.printf("  Numero: ");
		numero = scanner.nextInt();
		scanner.nextLine();

		System.out.printf("\n\tContatos\n");
		for (int i = 0; opcaoContatos != 2; i++) {
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
					System.out.printf("   Mais um contato?  (1) Sim  (2) Nao  ");
					opcaoContatos = scanner.nextInt();
					scanner.nextLine();
					if (opcaoContatos != 1 && opcaoContatos != 2)
						System.out.printf("   Opcao inv??lida");
				} while (opcaoContatos != 1 && opcaoContatos != 2);
			}
			contatos.add(new Contato(nomeContato, telefoneContato, emailContato));
		}
		System.out.println("   Formul??rio enviado com sucesso!!\n");

		return new Formulario(nome, email, cpf, new Endereco(cidade, bairro, rua, quadra, casa, cep, lote, numero, uf),
				escolaridade, contatos);
	}

	public static void construirATabela(ArrayList<Formulario> formularios) {
		System.out.println();
		for (int i = 0; i < formularios.size(); i++)
			System.out.println("  Formul??rio " + (i + 1) + "\tNome: " + formularios.get(i).getNome() + "    Email: "
					+ formularios.get(i).getEmail() + "\tEscolaridade: " + formularios.get(i).getEscolaridade());
		System.out.println();
	}

	private static int excluirFormularioDaTabela(ArrayList<Formulario> formularios, FormulariosController formulariosController, EnderecosController enderecosController, ContatosController contatosController, Scanner scanner,
												 Boolean excluirOuEditar) {

		int formularioASerExclu??do = 0;
		if (!podeExcluirDaTabela(formularios)) {
			System.out.println("  Tabela vazia!");
		}
		else {
			do {
				String k;
				if (excluirOuEditar)
					k = "excluir";
				else
					k = "editar";
				System.out.println("  Qual formulario deseja " + k + "? (-1 para voltar ao menu anterior)");
				formularioASerExclu??do = scanner.nextInt();
				scanner.nextLine();
				if (formularioASerExclu??do < 1 || formularioASerExclu??do > formularios.size()) {
					if (formularioASerExclu??do != -1)
						System.out.println("  Formul??rio Inexistente");
					else
						break;
				}
			} while (formularioASerExclu??do < 1 || formularioASerExclu??do > formularios.size());
			if (formularioASerExclu??do != -1) {
                formulariosController.excluir(formularios.get(formularioASerExclu??do - 1).getId());
				formularios.remove(formularioASerExclu??do - 1);
            }
		}
		return formularioASerExclu??do; // este ?? o formul??rio que foi exclu??do

	}

	public static void manipularATabela(ArrayList<Formulario> formularios, FormulariosController formulariosController, EnderecosController enderecosController, ContatosController contatosController, Scanner scanner) {
		if (!podeExcluirDaTabela(formularios)) {
			System.out.println("  Tabela vazia! Nenhum formul??rio foi preenchido.");
		}
		else {
			int opcao;
			do {
				construirATabela(formularios);
				System.out.println(
						"  O que deseja fazer?  (1) Excluir formulario da tabela  (2) Editar formulario  (3) Mostrar todos os dados de um formul??rio  (4) Voltar ao menu anterior  ");
				opcao = scanner.nextInt();
				scanner.nextLine();

				switch (opcao) {
					case 1:
						excluirFormularioDaTabela(formularios,formulariosController, enderecosController,contatosController, scanner, true);
						break;

					case 2:
						edirtarFormularioDaTabela(formularios,formulariosController, enderecosController,contatosController, scanner);
						break;

					case 3:
						mostrarTodosDadosDeUmFormulario(formularios, scanner);
						break;

					case 4:
						break;

					default:
						System.out.println(" Opcao Invalida");
				}
			} while (opcao < 1 || opcao > 4);

		}
	}

	private static void mostrarTodosDadosDeUmFormulario(ArrayList<Formulario> formularios, Scanner scanner) {
		int i = 0;
		do {
			System.out.println(" Qual formul??rio deseja mostrar? ");
			i = scanner.nextInt();
			if (i < 1 || i > formularios.size())
				System.out.println("  Formul??rio Inexistente!!");
		} while (i < 1 || i > formularios.size());
		formularios.get(i - 1).imprimirFormulario();
	}

	private static void edirtarFormularioDaTabela(ArrayList<Formulario> formularios, FormulariosController formulariosController, EnderecosController enderecosController, ContatosController contatosController, Scanner scanner) {
		int formularioASerEditado = 0;
		boolean precisaEditarATabelaFormularios=false,precisaEditarATabelaEndereco=false,precisaEditarATabelaContatos=false;
		do {
			System.out.println("  Qual formul??rio deseja editar? ");
			formularioASerEditado = scanner.nextInt();
			scanner.nextLine();
			if (formularioASerEditado < 1 || formularioASerEditado > formularios.size())
				System.out.println("  Formul??rio Inexistente");
		} while (formularioASerEditado < 1 || formularioASerEditado > formularios.size());

		Formulario formularioParaEditar = formularios.get(formularioASerEditado - 1);
		formularioParaEditar.imprimirFormulario();
		int opcao = 0, opcaoEndereco = 0, opcaoContatos = 0;
		do {
			System.out.println("\n  O que deseja editar? ");
			System.out.println("  (1) Nome");
			System.out.println("  (2) Email");
			System.out.println("  (3) Cpf");
			System.out.println("  (4) Endereco");
			System.out.println("  (5) Contatos");
			System.out.println("  (6) Voltar ao menu anterior");
			System.out.println("  Informe a opcao desejada: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					System.out.println("  Informe o novo nome: ");
					formularioParaEditar.setNome(scanner.nextLine());
					System.out.println("  Nome editado com sucesso!!");
					precisaEditarATabelaFormularios=true;
					break;

				case 2:
					System.out.println("  Informe o novo email: ");
					formularioParaEditar.setEmail(scanner.nextLine());
					System.out.println("  Email editado com sucesso!!");
					precisaEditarATabelaFormularios=true;
					break;

				case 3:
					boolean ??cpf = true;
					do {
						System.out.println("  Informe o novo Cpf: ");
						String novoCpf = scanner.nextLine();
						??cpf = isCPF(novoCpf);
						if (??cpf) {
							formularioParaEditar.setCpf(novoCpf);
							System.out.println("  Cpf editado com sucesso!!");
						} else {
							System.out.println(" Cpf Inv??lido");
						}
					} while (!??cpf);
					precisaEditarATabelaFormularios=true;
					break;

				case 4: {
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
						System.out.println("   Informe a opcao desejada: ");
						opcaoEndereco = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoEndereco) {
							case 1:
								System.out.println("  Informe a nova cidade: ");
								formularioParaEditar.getEndereco().setCidade(scanner.nextLine());
								System.out.println("  Cidade editado com sucesso!!");
								break;
							case 2:
								System.out.println("  Informe o novo bairro: ");
								formularioParaEditar.getEndereco().setBairro(scanner.nextLine());
								System.out.println("  Bairro editado com sucesso!!");
								break;
							case 3:
								System.out.println("  Informe a nova rua: ");
								formularioParaEditar.getEndereco().setRua(scanner.nextLine());
								System.out.println("  Rua editado com sucesso!!");
								break;
							case 4:
								System.out.println("  Informe a nova quadra: ");
								formularioParaEditar.getEndereco().setQuadra(scanner.nextInt());
								scanner.nextLine();
								System.out.println("  Quadra editado com sucesso!!");
								break;
							case 5:
								System.out.println("  Informe a nova casa: ");
								formularioParaEditar.getEndereco().setCasa(scanner.nextInt());
								scanner.nextInt();
								System.out.println("  Casa editado com sucesso!!");
								break;
							case 6:
								System.out.println("  Informe o novo cep: ");
								formularioParaEditar.getEndereco().setCep(scanner.next());
								scanner.nextLine();
								System.out.println("  Cep editado com sucesso!!");
								break;
							case 7:
								System.out.println("  Informe o novo lote: ");
								formularioParaEditar.getEndereco().setLote(scanner.nextInt());
								scanner.nextLine();
								System.out.println("  Lote editado com sucesso!!");
								break;
							case 8:
								System.out.println("  Informe o novo numero: ");
								formularioParaEditar.getEndereco().setNumero(scanner.nextInt());
								scanner.nextLine();
								System.out.println("  Numero editado com sucesso!!");
								break;
							case 9:
								System.out.println("  Informe o novo estado: ");
								formularioParaEditar.getEndereco().setUf(scanner.nextLine());
								System.out.println("  Numero editado com sucesso!!");
								break;
							default:
								System.out.println("  Opcao invalida!!");
						}////
					} while (opcaoEndereco < 1 || opcaoEndereco > 9);
					precisaEditarATabelaEndereco = true;
					break;
				}

				case 5:{

					do {
						System.out.println("  Qual contato deseja editar?");
						opcaoContatos = scanner.nextInt();
						scanner.nextLine();
						if (opcaoContatos < 1 || opcaoContatos > formularioParaEditar.getContatos().size()) {
							System.out.println("  Contato Inv??lido");
							break;
						} else {
							System.out.println("  Informe o novo nome: ");
							String nome = scanner.nextLine();
							System.out.println("  Informe o novo e-mail: ");
							String email = scanner.nextLine();
							System.out.println("  Informe o novo telefone: ");
							String telefone = scanner.nextLine();
							formularioParaEditar.getContatos().set(opcaoContatos - 1, new Contato(formularioParaEditar.getContatos().get(opcaoContatos - 1).getId(), formularioParaEditar.getContatos().get(opcaoContatos - 1).getIdFormulario(), nome, telefone, email));
						}
					} while (opcaoContatos < 1 || opcaoContatos > formularioParaEditar.getContatos().size());
					precisaEditarATabelaContatos=true;
				break;
				}

				case 6:
					break;

				default:
					System.out.println("	Opcao Inv??lida");
			}

			if (opcao == 6)
				break;

		} while (opcao < 1 || opcao > 6);

		formularios.set(formularioASerEditado - 1,formularioParaEditar);

		if(precisaEditarATabelaFormularios) {
			formulariosController.editar(formularioParaEditar.getId(), formularioParaEditar);
		}if(precisaEditarATabelaEndereco) {
			enderecosController.editar(formularioParaEditar.getId(), formularioParaEditar.getEndereco());
		}if(precisaEditarATabelaContatos) {
			contatosController.editar(formularioParaEditar.getContatos().get(opcaoContatos - 1).getId(), formularioParaEditar.getContatos().get(opcaoContatos - 1));
		}
	}

	private static boolean podeExcluirDaTabela(ArrayList<Formulario> formularios) {
		if (formularios.size() <= 0)
			return false;
		return true;
	}

	public static ArrayList<Formulario> retornarFormularioComOsDadosExistentesNoBanco(FormulariosController formulariosController , EnderecosController enderecosController, ContatosController contatosController){
		ArrayList<Formulario> f = formulariosController.listar();
		ArrayList<Endereco> e = enderecosController.listar();
		ArrayList<Contato> c = contatosController.listar();
		ArrayList<Contato> contatosASeremAdicionadosNoFormulario = new ArrayList<Contato>();

		for(int i = 0; i < f.size();i++){
			for (int k = 0 ; k < e.size();k++) {
				if (f.get(i).getId() == e.get(k).getIdFormulario()) {
					f.get(i).setEndereco(e.get(k));
				}
			}
		}
		System.out.println();
		for(int i = 0; i < f.size();i++){
			for(int k = 0 ; k < c.size(); k++) {
				if (f.get(i).getId() == c.get(k).getIdFormulario()) {
					contatosASeremAdicionadosNoFormulario.add(c.get(k));
				}
			}
			f.get(i).setContatos(contatosASeremAdicionadosNoFormulario);
			contatosASeremAdicionadosNoFormulario = new ArrayList<>();
		}
		return f;
	}

	public static void preencherEEnviarUmFormularioParaOBanco(ArrayList<Formulario> formularios, FormulariosController formulariosController, EnderecosController enderecosController, ContatosController contatosController, Scanner scanner) {
		//formul??rio preenchido no java
		Formulario formulario = FuncoesAuxiliares.preencherFormularioERetornalo(scanner);

		//formulario salvo no banco de dados e seu id ?? definido pelo retorno da funcao salvar para que fique de acordo com o que est?? no banco
		formulario.setId(formulariosController.salvar(formulario));

		//salvando o endereco e os contatos no banco
		enderecosController.salvar(formulario.getEndereco(), formulario.getId());
		for (int i = 0; i < formulario.getContatos().size(); i++) {
			formulario.getContatos().get(i).setId(contatosController.salvar(formulario.getContatos().get(i), formulario.getId()));
		}

		//arrumando os id's do endereco e dos contatos para que fique de acordo com o banco
		formulario.getEndereco().setIdFormulario(formulario.getId());
		for (int i = 0; i < formulario.getContatos().size(); i++) {
			formulario.getContatos().get(i).setIdFormulario(formulario.getId());
		}
		formularios.add(formulario);
	}

	public static void mostrarATabela_Editar_Excluir(ArrayList<Formulario> formularios, FormulariosController formulariosController, EnderecosController enderecosController, ContatosController contatosController, Scanner scanner) {
		FuncoesAuxiliares.manipularATabela(formularios, formulariosController, enderecosController, contatosController, scanner);
	}
}
