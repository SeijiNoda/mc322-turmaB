package mc322;

import java.util.Date;
import java.util.Scanner;

import mc322.cliente.Cliente;
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
		
		Seguradora seg1 = new Seguradora("Seguros do Jose", "46801563", "segjose@gmail.com", "Campinas - SP");
		System.out.println("Nome: " + seg1.getNome());
		System.out.println("Telefone: " + seg1.getTelefone());
		System.out.println("Email: " + seg1.getEmail());
		System.out.println("Endereco: " + seg1.getEndereco() + "\n");*/
		
		Scanner reader = new Scanner(System.in);
		String comando;
		do {
			System.out.println("========== Seguradora ==========");
			System.out.println("1. [cadastrar] Cadastrar cliente");
			System.out.println("2. [remover] Remover um sinistro");
			System.out.println("3. [sinistro] Criar um sinistro");
			System.out.println("4. [sair] Para sair do programa");
			comando = reader.nextLine();
			
			switch(comando.toLowerCase()) {
				case "sair": break;
				case "cadastrar": 
					System.out.print("Nome: ");
					String nome = reader.nextLine();
					
					System.out.print("Endereco: ");
					String endereco = reader.nextLine();
					
					Date dataLicensa = new Date();
				
					System.out.print("Pessoa Fisica ou Juridica? [PF/PJ]: ");
					//System.out.println("\n");
					String tipo = reader.next();
					System.out.print(tipo);
//					while (!(tipo.toUpperCase().equals("PF") | tipo.toUpperCase().equals("PJ"))) {
//						System.out.print("Pessoa Fisica ou Juridica? [PF/PJ]: ");
//						tipo = reader.next();
//					}
					
					break;
				case "remover":
					break;
				case "sinsitro":
					break;
			}
		} while (!comando.toLowerCase().equals("sair"));
		reader.close();
	}
}
	