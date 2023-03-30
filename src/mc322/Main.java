package mc322;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mc322.cliente.Cliente;
import mc322.cliente.ClientePF;
import mc322.cliente.ClientePJ;
import mc322.seguradora.Seguradora;
import mc322.sinistro.Sinistro;
import mc322.veiculo.Veiculo;

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
		
		Scanner reader = new Scanner(System.in);
		String comando;
		do {
			System.out.println("=========== Seguradora ===========");
			System.out.println("1. [clientes] Clientes");
			System.out.println("2. [sinistros] Sinistros");
			System.out.println("3. [sair] Sair do programa");
			comando = reader.nextLine();
			
			switch(comando.toLowerCase()) {
				case "sair": break;
				case "clientes": {
					String comandoInterno = "";
					do {
						System.out.println("============ Clientes ============");
						System.out.println("1.1 [cadastrar] Cadastrar cliente");
						System.out.println("1.2 [remover] Remover um cliente");
						System.out.println("1.3 [listar] Listar Clientes");
						System.out.println("1.4 [voltar] Voltar");
						comandoInterno = reader.nextLine();
						
						switch(comandoInterno.toLowerCase()) {
							case "voltar": break;
							case "cadastrar": {
								System.out.print("Nome: ");
								String nome = reader.nextLine();
								
								System.out.print("Endereco: ");
								String endereco = reader.nextLine();
								
								Date dataLicensa = new Date();
							
								String tipo = "";
								while (!(tipo.toUpperCase().equals("PF") | tipo.toUpperCase().equals("PJ"))) {
									System.out.print("Pessoa Fisica ou Juridica? [PF/PJ]: ");
									tipo = reader.nextLine();
								}
								
								if (tipo.toUpperCase().equals("PF")) {
									System.out.print("Educacao: ");
									String educacao = reader.nextLine();
									
									System.out.print("Genero: ");
									String genero = reader.nextLine();
									
									System.out.print("Classe Economica: ");
									String classeEconomica = reader.nextLine();
									
									System.out.print("CPF: ");
									String cpf = reader.nextLine();
									
									//System.out.print("Aniversario [dd/mm/yyyy]: ");
									//String dataNascimentoString = reader.nextLine();
									Date dataNascimento = new Date();
								
									ClientePF novoCliente = new ClientePF(nome, endereco, dataLicensa, educacao, genero, classeEconomica, cpf, dataNascimento);
									if (seguradora.cadastrarCliente(novoCliente)) {
										System.out.println("\nMENSAGEM: " + nome + "cadastrado com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Jah existe cliente com CPF " + cpf + ".\n");
									}
								} else {
									System.out.print("CNPJ: ");
									String cnpj = reader.nextLine();
									
									// concertar
									Date dataFundacao = new Date();
									
									ClientePJ novoCliente = new ClientePJ(nome, endereco, dataLicensa, "N/A", "N/A", "N/A", cnpj, dataFundacao);
									if (seguradora.cadastrarCliente(novoCliente)) {
										System.out.println("\nMENSAGEM: " + nome + " cadastrado com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Jah existe cliente com CNPJ " + cnpj + ".\n");
									}
								}
								
								break;
							}
							case "remover": {
								String tipo = "";
								while (!(tipo.toUpperCase().equals("PF") | tipo.toUpperCase().equals("PJ"))) {
									System.out.print("Pessoa Fisica ou Juridica? [PF/PJ]: ");
									tipo = reader.nextLine();
								}
								
								if (tipo.toUpperCase().equals("PF")) {
									System.out.print("CPF do cliente a remover: ");
									String cpf = reader.nextLine();
									
									ClientePF remover = new ClientePF("", "", new Date(), "", "", "", cpf, new Date());
									
									if (seguradora.removerCliente(remover)) {
										System.out.println("\nMENSAGEM: Cliente de CPF " + cpf + " removido com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Cliente de CPF " + cpf + " nao encontrado.\n");
									}
								} else {
									System.out.print("CNPJ do cliente a remover: ");
									String cnpj = reader.nextLine();
									
									ClientePJ remover = new ClientePJ("", "", new Date(), "", "", "", cnpj, new Date());
									
									if (seguradora.removerCliente(remover)) {
										System.out.println("\nMENSAGEM: Cliente de CNPJ " + cnpj + " removido com sucesso!\n");
									} else {
										System.out.println("\nMENSAGEM: Cliente de CNPJ" + cnpj + " nao encontrado.\n");
									}
								}
								break;
							}
							case "listar": {
								String tipo = "";
								while (!(tipo.toUpperCase().equals("PF") | tipo.toUpperCase().equals("PJ") | tipo.toUpperCase().equals("TODOS"))) {
									System.out.print("Deseja listar pessoas fisicas, juridicas ou todos? [PF/PJ/TODOS]: ");
									tipo = reader.nextLine();
								}
								
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
						}
					} while (!comandoInterno.toLowerCase().equals("voltar"));
							
					break;
				}
				case "sinistros": {
					String comandoInterno = "";
					do {
						System.out.println("=========== Sinistros ===========");
						System.out.println("1.1 [cadastrar] Cadastrar sinistro");
						System.out.println("1.2 [remover] Remover um sinitros");
						System.out.println("1.3 [listar] Listar sinistros");
						System.out.println("1.4 [voltar] Voltar");
						comandoInterno = reader.nextLine();
						
						switch(comandoInterno.toLowerCase()) {
							case "voltar": break;
						}
					} while(!comandoInterno.toLowerCase().equals("voltar"));
					
					break;
				}
			}
		} while (!comando.toLowerCase().equals("sair"));
		reader.close();
	}
}
	