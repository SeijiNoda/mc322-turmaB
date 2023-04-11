package mc322.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import mc322.cliente.ClientePF;
import mc322.cliente.ClientePJ;

import java.util.regex.Matcher;

// Classe par ler inputs do usuario
public class InputReader {
	// Objeto que a classe usara para ler do teclado
	private static Scanner reader = new Scanner(System.in);
	
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
	
	// Metodo que le a proxima String do teclado e valida se ela atende o formato do CPF e se tal CPF 
	// eh valido, se sim retorna a String com o CPF validado, se nao pergunta ateh validar
	public static String lerCPF() {
		// Regex que valida os formatos possiveis para o CPF
		Pattern padraoCpf = Pattern.compile("[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}");
		
		// String de retorno
		String ret = "";
		
		// Condicionais para o loop que vai continuar perguntado pelo CPF ateh este ser valido
		boolean regex = false;
		boolean ehValido = false;
		while (!regex || !ehValido) {
			System.out.print("CPF: ");
			ret = reader.nextLine();
			
			// Valida se ret segue o padrao Regex do CPF segundo padraoCpf
			Matcher matcher = padraoCpf.matcher(ret);
			regex = matcher.matches();
			// Utiliza da funcao static da classe ClientePF para validar os digitos do CPF
			ehValido = ClientePF.validarCPF(ret);
		}
		
		return ret;
	}
	
	// Mesmo metodo acima, sobrecarregado com um parametro para exibir uma mensagem ao usuario antes
	// de ler o CPF
	public static String lerCPF(String msg) {
		Pattern padraoCpf = Pattern.compile("[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		boolean ehValido = false;
		while (!regex || !ehValido) {
			System.out.print(msg);
			ret = reader.nextLine();
			
			Matcher matcher = padraoCpf.matcher(ret);
			regex = matcher.matches();
			ehValido = ClientePF.validarCPF(ret);
		}
		
		return ret;
	}
	
	// Metodo identico ao do CPF, porem com unicas mudancas o Regex do Pattern que agora atende ao CNPJ e 
	// a funcao de validacao que agora eh chamada de ClientePJ
	public static String lerCNPJ() {
		Pattern padraoCnpj = Pattern.compile("[0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		boolean ehValido = false;
		while (!regex || !ehValido) {
			System.out.print("CNPJ: ");
			ret = reader.nextLine();
			
			Matcher matcher = padraoCnpj.matcher(ret);
			regex = matcher.matches();
			ehValido = ClientePJ.validarCNPJ(ret);
		}
		
		return ret;
	}
	
	// Mesmo metodo acima, sobrecarregado com um parametro para exibir uma mensagem ao usuario antes
	// de ler o CNPJ
	public static String lerCNPJ(String msg) {
		Pattern padraoCnpj = Pattern.compile("[0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		boolean ehValido = false;
		while (!regex || !ehValido) {
			System.out.print(msg);
			ret = reader.nextLine();
			
			Matcher matcher = padraoCnpj.matcher(ret);
			regex = matcher.matches();
			ehValido = ClientePJ.validarCNPJ(ret);
		}
		
		return ret;
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
		// regex bem complicado adaptado de https://stackoverflow.com/questions/15491894/regex-to-validate-date-formats-dd-mm-yyyy-dd-mm-yyyy-dd-mm-yyyy-dd-mmm-yyyy
		// adaptado para corresponder apenas a exatamente o formato dd/mm/yyyy
		Pattern padraoData = Pattern.compile("(?:(?:31(\\/)(?:0[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)\\d{2})$|^(?:29(\\/)02\\3(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0[1-9]|1\\d|2[0-8])(\\/)(?:(?:0[1-9])|(?:1[0-2]))\\4(?:1[6-9]|[2-9]\\d\\d{2})");
		String dataStr = "";
		boolean regex = false;
		while (!regex) {
			System.out.print(msg);
			dataStr = reader.nextLine();
			
			Matcher matcher = padraoData.matcher(dataStr);
			regex = matcher.matches();
		}
		
		return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	// Metodo que le e retorna uma String de placa de carro que se adequa ao Regex abaixo (BR e Mercosul)
	public static String lerPlaca() {
		Pattern padraoPlaca = Pattern.compile("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}");
		String placa = "";
		boolean regex = false;
		while (!regex) {
			System.out.print("Placa: ");
			placa = reader.nextLine();
			
			Matcher matcher = padraoPlaca.matcher(placa);
			regex = matcher.matches();
		}
		
		return placa;
	}
	
	// Mesmo metodo acima, porem sobrecarregado com um parametro para exibir uma mensagem ao
	// usuario antes de ler a placa
	public static String lerPlaca(String msg) {
		Pattern padraoPlaca = Pattern.compile("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}");
		String placa = "";
		boolean regex = false;
		while (!regex) {
			System.out.print(msg);
			placa = reader.nextLine();
			
			Matcher matcher = padraoPlaca.matcher(placa);
			regex = matcher.matches();
		}
		
		return placa;
	}
}
