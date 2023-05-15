package mc322.leitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import mc322.cliente.ClientePF;
import mc322.cliente.ClientePJ;

import java.util.regex.Matcher;

public final class Validacao {
	public static boolean validarNome(String nome) {
		return nome.equals("") ? false : nome.replaceAll("[A-Za-z ]", "").trim().length() == 0;
	}
	
	public static boolean validarEmail(String email) {
		Pattern padraoEmail = Pattern.compile("^(.+)@(.+)$");
		
		Matcher matcher = padraoEmail.matcher(email);
		return matcher.matches();
	}
	
	public static boolean validarCPF(String cpf) {
		// Regex que valida os formatos possiveis para o CPF
		Pattern padraoCpf = Pattern.compile("[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}");
			
		// Valida se ret segue o padrao Regex do CPF segundo padraoCpf
		Matcher matcher = padraoCpf.matcher(cpf);
		boolean regex = matcher.matches();
		// Utiliza da funcao static da classe ClientePF para validar os digitos do CPF
		boolean ehValido = ClientePF.validarCPF(cpf);

		return regex && ehValido;
	}
	
	public static boolean validarCNPJ(String cnpj) {
		Pattern padraoCnpj = Pattern.compile("[0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2}");
		
		Matcher matcher = padraoCnpj.matcher(cnpj);
		boolean regex = matcher.matches();
		boolean ehValido = ClientePJ.validarCNPJ(cnpj);
		
		return regex && ehValido;
	}
	
	public static boolean validarData(String data) {
		// regex bem complicado adaptado de https://stackoverflow.com/questions/15491894/regex-to-validate-date-formats-dd-mm-yyyy-dd-mm-yyyy-dd-mm-yyyy-dd-mmm-yyyy
		// adaptado para corresponder apenas a exatamente o formato dd/mm/yyyy
		Pattern padraoData = Pattern.compile("(?:(?:31(\\/)(?:0[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)\\d{2})$|^(?:29(\\/)02\\3(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0[1-9]|1\\d|2[0-8])(\\/)(?:(?:0[1-9])|(?:1[0-2]))\\4(?:1[6-9]|[2-9]\\d\\d{2})");
		Matcher matcher = padraoData.matcher(data);
		return matcher.matches();
	}
	
	public static boolean validarPlaca(String placa) {
		Pattern padraoPlaca = Pattern.compile("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}");
		Matcher matcher = padraoPlaca.matcher(placa);
		return matcher.matches();
	}
}
