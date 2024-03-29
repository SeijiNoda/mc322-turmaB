	package mc322.cliente;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import mc322.seguradora.CalcSeguro;
import mc322.veiculo.Veiculo;

public class ClientePF extends Cliente{
	private final String cpf;
	private LocalDate dataNascimento;
	private String educacao;
	private String genero;
	private List<Veiculo> listaVeiculos;
	
	public ClientePF(String nome, String telefone, String endereco, String email, String cpf, LocalDate dataNascimento,
			String educacao, String genero) {
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.educacao = educacao;
		this.genero = genero;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public static boolean validarCPF(String cpf) {
		// removemos todos os caracteres nao numericos com o regex
		cpf = cpf.replaceAll("[^0-9 ]", "");
				
		// verificamos o comprimento da string parametro
		if (cpf.length() != 11) return false;
		
		// verificamos se todos os caracteres nao sao iguais
		for (int i = 0; i < 11; i++) {
			if (cpf.charAt(0) != cpf.charAt(i)) break;
			if (i == 10) return false;
		}
		
		// calculamos os digitos verificadores

		int primeiroVerificador = calcularDigitoVerificador(cpf.substring(0, 9));
		int segundoVerificador = calcularDigitoVerificador(cpf.substring(1, 10));
		
		// verificamos se os digitos finais passados batem com os calculados
		if (Character.getNumericValue(cpf.charAt(9)) != primeiroVerificador) return false;
		if (Character.getNumericValue(cpf.charAt(10)) != segundoVerificador) return false;
		
		// se passar de todas verificacoes retornamos true
		return true;
	}

	private static int calcularDigitoVerificador(String numeros) {
		// verificamos o comprimento da string parametro
		if (numeros.length() != 9) return -1;
		
		// inicalizamos a variavel "soma" que armazenara os resultados das multiplicacoes
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			// calculamos a soma do digito verificador
			int atual = Character.getNumericValue(numeros.charAt(i));		
			
			soma += atual * (10 - i);
		}			
		
		// pegamos o resto da soma / 11
		int resto = soma % 11;
		
		// caso seja 1 ou 0 (resultado de % sempre >= 0) retornamos 0
		if (resto <= 1) return 0;
		
		// caso contrario, retornamos d1 = 11 - resto
		return 11 - resto;
	}
	
	// Metodo boolean para adicionar um novo veiculo na lista de veiculos do cliente
	public boolean cadastrarVeiculo(Veiculo novo) {
		// Percorre a lista de veiculos para ver se a placa do novo veiculo nao eh repetida
		for (Veiculo veiculo: this.listaVeiculos) {
			if (veiculo.getPlaca().equals(novo.getPlaca())) return false;
		}
		
		// Se nao for, adiciona o novo veiculo a lisa
		this.listaVeiculos.add(novo);
		return true;
	}
		
	// Metodo boolean para remover um veiculo dado sua placa
	public boolean removerVeiculo(String placa) {
		// Percorremos a lista de veiculos ateh achar um veiculo com a placa desejada, e entao o removemos
		for (Veiculo veiculo: this.listaVeiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				this.listaVeiculos.remove(veiculo);
				return true;
			}
		}
		
		// Senao encontrarmos, retornamos false
		return false;
	}
	
	public int getIdade() {
		return Period.between(this.dataNascimento, LocalDate.now()).getYears();
	}
	
	@Override
	public String toString() {
		String ret = String.format("Nome: %s\nCPF: %s\nAniversario: %s", this.getNome(), this.getCpf(), this.getDataNascimento().toString());
		
		int nmrVeiculos = this.getListaVeiculos().size();
		if (nmrVeiculos > 0) {
			ret += "\nVeiculos: [\n";
			for (Veiculo veiculo: this.getListaVeiculos()) {
				ret += veiculo.toString();
				if (--nmrVeiculos > 0) {
					ret += ",\n";
				} else ret += "\n";
			}
			ret += "]";
		}
		
		return ret;
	}
}
