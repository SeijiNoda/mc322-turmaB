package mc322.utils;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputReader {
	private static Scanner reader = new Scanner(System.in);
	
	public static String lerString() {
		return reader.nextLine();
	}
	
	public static String lerString(String msg) {
		System.out.print(msg);
		return reader.nextLine();
	}
	
	public static String lerCPF() {
		Pattern padraoCpf = Pattern.compile("[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		while (!regex) {
			System.out.print("CPF: ");
			ret = reader.nextLine();
			
			Matcher matcher = padraoCpf.matcher(ret);
			regex = matcher.matches();
		}
		
		return ret;
	}
	
	public static String lerCPF(String msg) {
		Pattern padraoCpf = Pattern.compile("[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		while (!regex) {
			System.out.print(msg);
			ret = reader.nextLine();
			
			Matcher matcher = padraoCpf.matcher(ret);
			regex = matcher.matches();
		}
		
		return ret;
	}
	
	public static String lerCNPJ() {
		Pattern padraoCnpj = Pattern.compile("[0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		while (!regex) {
			System.out.print("CNPJ: ");
			ret = reader.nextLine();
			
			Matcher matcher = padraoCnpj.matcher(ret);
			regex = matcher.matches();
		}
		
		return ret;
	}
	
	public static String lerCNPJ(String msg) {
		Pattern padraoCnpj = Pattern.compile("[0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2}");
		
		String ret = "";
		boolean regex = false;
		while (!regex) {
			System.out.print(msg);
			ret = reader.nextLine();
			
			Matcher matcher = padraoCnpj.matcher(ret);
			regex = matcher.matches();
		}
		
		return ret;
	}
	
	public static String lerTipoCliente() {
		String tipo = "";
		while (!(tipo.toUpperCase().equals("PF") | tipo.toUpperCase().equals("PJ"))) {
			System.out.print("Pessoa Fisica ou Juridica? [PF/PJ]: ");
			tipo = reader.nextLine();
		}
		
		return tipo.toUpperCase();
	}
	
	public static LocalDate lerData(String msg) {
		return LocalDate.now();
	}
}
