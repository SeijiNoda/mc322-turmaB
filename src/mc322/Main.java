package mc322;

import java.time.LocalDate;
import java.util.List;

import mc322.cliente.Cliente;
import mc322.cliente.ClientePF;
import mc322.cliente.ClientePJ;
import mc322.seguradora.Seguradora;
import mc322.sinistro.Sinistro;
import mc322.veiculo.Veiculo;
import mc322.utils.InputReader;

public class Main {

	public static void main(String[] args) {
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
		ClientePF cliente2 = new ClientePF("Joao", "Rua dos Joaos - 111", LocalDate.now(), "EM", "M", "Media", "976.573.830-70", LocalDate.now());
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
								String nome = InputReader.lerString("Nome: ");
								
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
								String tipo = InputReader.lerTipoCliente();
								
								if (tipo.equals("PF")) {
									String cpf = InputReader.lerCPF();

									if (seguradora.removerCliente(cpf)) {
										System.out.println("\nMENSAGEM: Cliente de CPF " + cpf + " removido com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Cliente de CPF " + cpf + " nao encontrado.\n");
									}
								} else {
									String cnpj = InputReader.lerCNPJ();

									if (seguradora.removerCliente(cnpj)) {
										System.out.println("\nMENSAGEM: Cliente de CNPJ " + cnpj + " removido com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Cliente de CNPJ" + cnpj + " nao encontrado.\n");
									}
								}
								break;
							}
							case "listar": {
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
								
								if (seguradora.adicionarVeiculo(novo, key)) {
									System.out.println("\nMENSAGEM: Veiculo novo adicionado com sucesso!\n");
								} else {
									System.out.println("\nMENSAGEM: Cliente jah possue veiculo com placa: " + placa +".\n");
								}
								
								break;
							}
							case "remover": {
								String tipo = InputReader.lerTipoCliente();
								
								String key = "";
								if (tipo.equals("PF"))									
									key = InputReader.lerCPF("CPF do cliente de onde remover: ");
								else 
									key = InputReader.lerCNPJ("CNPJ do cliente de onde remover: ");
								
								String placa = InputReader.lerPlaca("Placa do veiculo a remover: ");
								
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
								
								String key = "";
								
								String tipo = InputReader.lerTipoCliente();
								
								if (tipo.equals("PF")) 
									key = InputReader.lerCPF();
								else 
									key = InputReader.lerCNPJ();
																
								String placa = InputReader.lerPlaca("Placa do veiculo do acidente: ");
								
								LocalDate data = InputReader.lerData("Data do acidente: [dd/mm/yyyy] ");
								
								String endereco = InputReader.lerString("Endereco do acidente: ");
								
								if (seguradora.gerarSinsitro(data, placa, endereco, key)) {
									System.out.println("\nMENSAGEM: Sinistro registrado com sucesso!\n");
								} else {
									System.out.println("\nMENSAGEM: Falha ao cadastrar sinistro.\nVerifique os dados e tente novamente.\n");
								}
								
								break;
							}
							case "verificar": {
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
					System.out.println(seguradora.toString());				
					break;
				}
			}
		} while (!comando.toLowerCase().equals("sair"));
	}
}
	