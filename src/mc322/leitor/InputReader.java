package mc322.leitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import mc322.seguradora.Seguradora;

import java.util.regex.Matcher;

// Classe par ler inputs do usuario
public class InputReader {
	// Objeto que a classe usara para ler do teclado
	private static Scanner reader = new Scanner(System.in);
	
	public static int lerInteiro() {
		return reader.nextInt();
	}
	
	// Metodo que le a proxima String do teclado
	public static String lerString() {
		return reader.nextLine();
	}
	
	// Sobrecarga do metodo acima para tambem exibir uma mensagem passado pelo parametro msg antes
	// de ler a proxima String do teclado
	public static String lerString(String msg) {
		System.out.print(msg);
		return reader.nextLine();
	}

	// Metodo que le a proxima String do teclado e valida se ela possui apenas letras para o nome
	public static String lerNome() {
		String ret = "";
		
		while(!Validacao.validarNome(ret)) {
			System.out.print("Nome: ");
			ret = reader.nextLine();
		}
		
		return ret;
	}
	
	public static String lerEmail() {
		String ret = "";
		
		while(!Validacao.validarEmail(ret)) {
			System.out.print("Email: ");
			ret = reader.nextLine();
		}
		
		return ret;
	}
	
	// Metodo que le a proxima String do teclado e valida se ela atende o formato do CPF e se tal CPF 
	// eh valido, se sim retorna a String com o CPF validado, se nao pergunta ateh validar
	public static String lerCPF() {
		// String de retorno
		String ret = "";
		
		// Le uma String CPF enquanto este nao for valido
		while (!Validacao.validarCPF(ret)) {
			System.out.print("CPF: ");
			ret = reader.nextLine();
		}
		
		return ret.replaceAll("[^0-9]", "");
	}
	
	// Mesmo metodo acima, sobrecarregado com um parametro para exibir uma mensagem ao usuario antes
	// de ler o CPF
	public static String lerCPF(String msg) {
		String ret = "";
		while (!Validacao.validarCPF(ret)) {
			System.out.print(msg);
			ret = reader.nextLine();
		}
		
		return ret.replaceAll("[^0-9]", "");
	}
	
	// Metodo identico ao do CPF, porem com unicas mudancas o Regex do Pattern que agora atende ao CNPJ e 
	// a funcao de validacao que agora eh chamada de ClientePJ
	public static String lerCNPJ() {
		String ret = "";
		while (!Validacao.validarCNPJ(ret)) {
			System.out.print("CNPJ: ");
			ret = reader.nextLine();
		}
		
		return ret.replaceAll("[^0-9]", "");
	}
	
	// Mesmo metodo acima, sobrecarregado com um parametro para exibir uma mensagem ao usuario antes
	// de ler o CNPJ
	public static String lerCNPJ(String msg) {
		String ret = "";
		while (!Validacao.validarCNPJ(ret)) {
			System.out.print(msg);
			ret = reader.nextLine();
		}
		
		return ret.replaceAll("[^0-9]", "");
	}
	
	// Metodo que le uma String dentro do formato "PF" ou "PJ" e retorna tal String
	public static String lerTipoCliente() {
		String tipo = "";
		while (!(tipo.toUpperCase().equals("PF") | tipo.toUpperCase().equals("PJ"))) {
			System.out.print("Pessoa Fisica ou Juridica? [PF/PJ]: ");
			tipo = reader.nextLine();
		}
		
		return tipo.toUpperCase();
	}
	
	// Metodo que le um String no formato [dd/mm/yyyy] valida e a converte para um 
	// objeto LocalDate que entao retorna
	public static LocalDate lerData(String msg) {
		String dataStr = "";
		while (!Validacao.validarData(dataStr)) {
			System.out.print(msg);
			dataStr = reader.nextLine();
		}
		
		return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	// Metodo que le e retorna uma String de placa de carro que se adequa ao Regex abaixo (BR e Mercosul)
	public static String lerPlaca() {
		String placa = "";
		while (!Validacao.validarPlaca(placa)) {
			System.out.print("Placa: ");
			placa = reader.nextLine();
		}
		
		return placa;
	}
	
	// Mesmo metodo acima, porem sobrecarregado com um parametro para exibir uma mensagem ao
	// usuario antes de ler a placa
	public static String lerPlaca(String msg) {
		String placa = "";
		while (!Validacao.validarPlaca(placa)) {
			System.out.print(msg);
			placa = reader.nextLine();
		}
		
		return placa;
	}
}
