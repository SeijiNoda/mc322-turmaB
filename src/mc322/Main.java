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
		// instanciacao dos objetos das Classes Cliente, Veiculo, Sinistro, Seguradora e exibicao de suas propriedades
		/*Cliente cli1 = new Cliente("Seiji", "502.289.268-58", "14/01/2004", 19, "Campinas - SP");
		System.out.println(cli1.toString() + "\n");
	
		Veiculo vei1 = new Veiculo("EVR-8404", "Astra", "Chevrolet");
		System.out.println("Placa: " + vei1.getPlaca());
		System.out.println("Modelo: " + vei1.getModelo());
		System.out.println("Marca: " + vei1.getMarca() + "\n");
		
		Sinistro sin1 = new Sinistro("14/03/2023", "Curitiba - PR");
		System.out.println("ID: " + sin1.getId());
		System.out.println("Data: " + sin1.getData());
		System.out.println("Endereco: " + sin1.getEndereco() + "\n");
		
		*/
		Seguradora seguradora = new Seguradora("Seguros do Jose", "46801563", "segjose@gmail.com", "Campinas - SP");
//		System.out.println("Nome: " + seg1.getNome());
//		System.out.println("Telefone: " + seg1.getTelefone());
//		System.out.println("Email: " + seg1.getEmail());
//		System.out.println("Endereco: " + seg1.getEndereco() + "\n");
		
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
									
									ClientePJ novoCliente = new ClientePJ(nome, endereco, dataLicensa, "N/A", "N/A", "N/A", cnpj, dataFundacao);
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
									
									ClientePF remover = new ClientePF("", "", LocalDate.now(), "", "", "", cpf, LocalDate.now());
									
									if (seguradora.removerCliente(remover)) {
										System.out.println("\nMENSAGEM: Cliente de CPF " + cpf + " removido com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Cliente de CPF " + cpf + " nao encontrado.\n");
									}
								} else {
									String cnpj = InputReader.lerCNPJ();
									
									ClientePJ remover = new ClientePJ("", "", LocalDate.now(), "", "", "", cnpj, LocalDate.now());
									
									if (seguradora.removerCliente(remover)) {
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
								
								if (tipo.equals("PF")) {
									String cpf = InputReader.lerCPF("CPF do cliente ao qual adicionar: ");
									
									
								} else {
									String cnpj = InputReader.lerCNPJ("CNPJ do cliente ao qual adicionar: ");
									
									
								}
								
								break;
							}
							case "remover": {
								String tipo = InputReader.lerTipoCliente();
								
								String key = "";
								if (tipo.equals("PF"))									
									key = InputReader.lerCPF("CPF do cliente ao qual adicionar: ");
								else 
									key = InputReader.lerCNPJ("CNPJ do cliente ao qual adicionar: ");
								
								String placa = InputReader.lerPlaca("Placa do veiculo a remover: ");
								
								if (seguradora.removerVeiculo(placa, tipo, key)) {
									System.out.println("\nMENSAGEM: Veiculo de placa " + placa + " removido com sucesso!\n");
								} else {
									System.out.println("\nMENSAGEM: Nao encontramos veiculo com tal placa\n");
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
						System.out.println("2.2 [remover] Remover um sinitros");
						System.out.println("2.3 [listar] Listar sinistros");
						System.out.println("2.4 [voltar] Voltar");
						comandoInterno = InputReader.lerString();
						
						switch(comandoInterno.toLowerCase()) {
							case "voltar": break;
						}
					} while(!comandoInterno.toLowerCase().equals("voltar"));
					
					break;
				}
				case "exibir": {
					System.out.println("/n" + seguradora.toString() + "/n");				
					break;
				}
			}
		} while (!comando.toLowerCase().equals("sair"));
	}
}
	