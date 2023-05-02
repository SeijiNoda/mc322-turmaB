package mc322;

import java.time.LocalDate;
import java.util.List;

import mc322.cliente.Cliente;
import mc322.cliente.ClientePF;
import mc322.cliente.ClientePJ;
import mc322.leitor.InputReader;
import mc322.seguradora.Seguradora;
import mc322.sinistro.Sinistro;
import mc322.veiculo.Veiculo;

public class Main {

	public static void main(String[] args) {
		// Instanciamos alguns valores para o programa logo abaixo já possuir alguns objetos quando iniciar
		
		// Instanciar pelo menos 1 objeto da classe Seguradora
		Seguradora seguradora = new Seguradora("Seguros do Jose", "46801563", "segjose@gmail.com", "Campinas - SP");
		
		// Cadastrar e remover pelo menos um Cliente (ClientePF ou ClientePJ)
		//   System.out.println(seguradora.toString());
		ClientePF cliente1 = new ClientePF("Joao", "Rua dos Joaos - 111", LocalDate.now(), "EM", "M", "Media", "603.106.490-13", LocalDate.now());
		seguradora.cadastrarCliente(cliente1);
		//   System.out.println(seguradora.toString());
		seguradora.removerCliente(cliente1.getCpf());
		//   System.out.println(seguradora.toString());
		
		// Cadastrar pelo menos 2 clientes em Seguradora (sem remover), sendo 1 do tipo ClientePF e 1 do tipo ClientePJ;
		ClientePF cliente2 = new ClientePF("Joao", "Rua dos Joaos - 111", LocalDate.now(), "EM", "M", "Media", "97657383070", LocalDate.now());
		seguradora.cadastrarCliente(cliente2);
		ClientePJ cliente3 = new ClientePJ("Venda do Joao", "Rua dos Joaos - 111", LocalDate.now(), "86.658.785/0001-00", LocalDate.now());
		seguradora.cadastrarCliente(cliente3);
		//   System.out.println(seguradora.toString());
		
		// Adicionar pelo menos 1 Veiculo em cada Cliente instanciado
		Veiculo veiculo1 = new Veiculo("ABC1234", "Fiat", "Fusca", 1983);
		seguradora.adicionarVeiculo(veiculo1, cliente2.getCpf());
		//   System.out.println(cliente2.toString());
		Veiculo veiculo2 = new Veiculo("ABC1D23", "Calloi", "Bicicleta do grau", 2000);
		seguradora.adicionarVeiculo(veiculo2, cliente3.getCnpj());
		//   System.out.println(cliente3.toString());
		
		
		/*
		 * Aqui explico a logica por tras de todos os menus:
		 *    Temos aqui um do-while cujo condicional eh uma igualdade entre a String comando,
		 *    que recebe uma String digitada pelo usuario, e um comando estabelecido por mim para
		 *    ser um comando de parada.
		 * Neste caso inicial, temos o menu principal executando ateh que a String comando assuma
		 * o valor "sair". No menu de clientes, o comando de parada eh "voltar". etc.  
		 * 
		 * Alem do loop do menu, temos em cada um deles um switch-case dependente do comando lido,
		 * que vai executar o comportamento adequado para cada comando
		 * */		
		
		// Menu de opcoes GERAL
		String comando;
		do {
			System.out.println("=========== Seguradora ===========");
			System.out.println("1. [clientes] Clientes");
			System.out.println("2. [sinistros] Sinistros");
			System.out.println("3. [exibir] Dados da seguradora");
			System.out.println("4. [sair] Sair do programa");
			comando = InputReader.lerString();
			
			switch(comando.toLowerCase()) {
				case "sair": break;
				case "clientes": {
					// Menu de opcoes para CLIENTES
					
					String comandoInterno = "";
					do {
						System.out.println("============ Clientes ============");
						System.out.println("1.1 [cadastrar] Cadastrar cliente");
						System.out.println("1.2 [deletar] Remover um cliente");
						System.out.println("1.3 [listar] Listar Clientes");
						System.out.println("1.4 [adicionar] Adicionar veiculo");
						System.out.println("1.5 [remover] Remover veiculo");
						System.out.println("1.6 [voltar] Voltar");
						comandoInterno = InputReader.lerString();
						
						switch(comandoInterno.toLowerCase()) {
							case "voltar": break;
							case "cadastrar": {
								// Tratamento do comando de cadastro, pedimos as informacoes gerais e depois as especificas,
								// e assim respondemos de acordo com resultado da funcao de cadastrar
								
								String nome = InputReader.lerNome();
								
								String endereco = InputReader.lerString("Endereco: ");
								
								LocalDate dataLicensa = LocalDate.now();
							
								String tipo = InputReader.lerTipoCliente();
								
								if (tipo.equals("PF")) {
									String educacao = InputReader.lerString("Educacao: ");
									
									String genero = InputReader.lerString("Genero: ");
									
									String classeEconomica = InputReader.lerString("Classe Economica: ");
									
									String cpf = InputReader.lerCPF();
;									
									LocalDate dataNascimento = InputReader.lerData("Aniversario: [dd/mm/yyyy] ");
								
									ClientePF novoCliente = new ClientePF(nome, endereco, dataLicensa, educacao, genero, classeEconomica, cpf, dataNascimento);
									if (seguradora.cadastrarCliente(novoCliente)) {
										System.out.println("\nMENSAGEM: " + nome + " cadastrado com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Jah existe cliente com CPF " + cpf + ".\n");
									}
								} else {
									String cnpj = InputReader.lerCNPJ();
									
									LocalDate dataFundacao = InputReader.lerData("Data de fundacao: [dd/mm/yyyy]");
									
									ClientePJ novoCliente = new ClientePJ(nome, endereco, dataLicensa, cnpj, dataFundacao);
									if (seguradora.cadastrarCliente(novoCliente)) {
										System.out.println("\nMENSAGEM: " + nome + " cadastrado com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Jah existe cliente com CNPJ " + cnpj + ".\n");
									}
								}
								
								break;
							}
							case "deletar": {
								// Aqui tentamos deletar um cliente dado seu CPF/CNPJ
								
								String tipo = InputReader.lerTipoCliente();

								String key = "";
								
								if (tipo.equals("PF"))
									key = InputReader.lerCPF();	
								else 
									key = InputReader.lerCNPJ();
								
								String ret;
								if (seguradora.removerCliente(key)) {
									ret = String.format("\nMENSAGEM: Cliente de %s %s removido com sucesso!\n", tipo.equals("PF") ? "CPF" : "CNPJ" ,key);
								} else {
									ret = String.format("\nMENSAGEM: Cliente de %s %s nao encontrado.\n", tipo.equals("PF") ? "CPF" : "CNPJ", key);
								}
								
								System.out.println(ret);
								
								break;
							}
							case "listar": {
								// Aqui listamos todos os clientes segundo um filtro de seu tipo (PF ou PJ)
								
								String tipo = InputReader.lerTipoCliente();
								
								List<Cliente> lista = seguradora.listarClientes(tipo);
								if (lista.size() == 0) {
									System.out.println("\nMENSAGEM: Lista de clientes vazia!\n");
								} else {
									for (Cliente cliente: lista) {
										System.out.println(cliente.toString() + "\n");
									}
								}
								
								break;
							}
							case "adicionar": {
								// Aqui tentamos adicionar um novo veiculo a um cliente dado as informacoes pedidas abaixo
								
								String tipo = InputReader.lerTipoCliente();
								
								String key = "";
								if (tipo.equals("PF")) 
									key = InputReader.lerCPF("CPF do cliente ao qual adicionar: ");
								 else 
									key = InputReader.lerCNPJ("CNPJ do cliente ao qual adicionar: ");
								
								String placa = InputReader.lerPlaca();
								
								String marca = InputReader.lerString("Marca: ");
								
								String modelo = InputReader.lerString("Modelo: ");
								
								int anoFabricacao = Integer.parseInt(InputReader.lerString("Ano de fabricacao: "));
								
								Veiculo novo = new Veiculo(placa, marca, modelo, anoFabricacao);
								
								// Verificamos o resultado do metodo de adicionar e informamos o usuario de acordo
								if (seguradora.adicionarVeiculo(novo, key)) {
									System.out.println("\nMENSAGEM: Veiculo novo adicionado com sucesso!\n");
								} else {
									System.out.println("\nMENSAGEM: Cliente jah possue veiculo com placa: " + placa +".\n");
								}
								
								break;
							}
							case "remover": {
								// Aqui tentamos remover veiculo de um cliente dado as informacoes pedidas abaixo
								
								String tipo = InputReader.lerTipoCliente();
								
								String key = "";
								if (tipo.equals("PF"))									
									key = InputReader.lerCPF("CPF do cliente de onde remover: ");
								else 
									key = InputReader.lerCNPJ("CNPJ do cliente de onde remover: ");
								
								String placa = InputReader.lerPlaca("Placa do veiculo a remover: ");
								
								// Verificamos o resultado da tentativa de remocao e informamos o usuario de acordo
								if (seguradora.removerVeiculo(placa, key)) {
									System.out.println("\nMENSAGEM: Veiculo de placa " + placa + " removido com sucesso!\n");
								} else {
									System.out.println("\nMENSAGEM: Nao encontramos veiculo com placa: " + placa + ".\n");
								}
								
								break;
							}
						}
					} while (!comandoInterno.toLowerCase().equals("voltar"));
							
					break;
				}
				case "sinistros": {
					// Menu de opcoes para os SINISTROS
					
					String comandoInterno = "";
					do {
						System.out.println("=========== Sinistros ===========");
						System.out.println("2.1 [cadastrar] Cadastrar sinistro");
						System.out.println("2.2 [verificar] Verificar se existe sinistro");
						System.out.println("2.3 [listar] Listar sinistros");
						System.out.println("2.4 [voltar] Voltar");
						comandoInterno = InputReader.lerString();
						
						switch(comandoInterno.toLowerCase()) {
							case "voltar": break;
							case "cadastrar": {
								// Aqui tentamos cadastrar um novo sinistro
								
								// Dados para o novo sinistro
								String key = "";
								
								String tipo = InputReader.lerTipoCliente();
								
								if (tipo.equals("PF")) 
									key = InputReader.lerCPF();
								else 
									key = InputReader.lerCNPJ();
																
								String placa = InputReader.lerPlaca("Placa do veiculo do acidente: ");
								
								LocalDate data = InputReader.lerData("Data do acidente: [dd/mm/yyyy] ");
								
								String endereco = InputReader.lerString("Endereco do acidente: ");
								
								// Verificamos o resultado de nossa tentativa de cadastro e informamos o usuario de acordo com tal resutlado
								if (seguradora.gerarSinsitro(data, placa, endereco, key)) {
									System.out.println("\nMENSAGEM: Sinistro registrado com sucesso!\n");
								} else {
									System.out.println("\nMENSAGEM: Falha ao cadastrar sinistro.\nVerifique os dados e tente novamente.\n");
								}
								
								break;
							}
							case "verificar": {
								// Aqui verificamos se existe sinistro para certo cliente
								
								String key = "";
								
								String tipo = InputReader.lerTipoCliente();
								
								if (tipo.equals("PF")) 
									key = InputReader.lerCPF();
								else
									key = InputReader.lerCNPJ();
								
								boolean existeSinistro = seguradora.vizualizarSinistro(key);
								
								if (existeSinistro) {
									System.out.println("\nMENSAGEM: Existe pelo menos um sinsitro do cliente " + key + "\n");
								} else {
									System.out.println("\nMENSAGEM: Nao existe sinistro para o cliente " + key + "\n");
								}
								
								break;
							}
							case "listar": {
								// Aqui listamos todos os sinistros da seguradora que foram retornados pela chamada de metodo abaixo
								
								List<Sinistro> sinistros = seguradora.listarSinistros();
								
								if (sinistros.size() <= 0) {
									System.out.println("\nMENSAGEM: Nenhum sinistro cadastrado.\n");
								}
								
								for (Sinistro sinistro: sinistros) {
									System.out.println(sinistro.toString());
								}
												
								break;
							}
						}
					} while(!comandoInterno.toLowerCase().equals("voltar"));
					
					break;
				}
				case "exibir": {
					// Caso para o comando "exibir", que mostra os detalhes da Seguradora em questao
					
					System.out.println(seguradora.toString());				
					break;
				}
			}
		} while (!comando.toLowerCase().equals("sair"));
	}
}
	