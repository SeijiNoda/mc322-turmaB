package mc322.cliente;

import java.time.LocalDate;
import java.util.List;

import mc322.seguradora.CalcSeguro;
import mc322.veiculo.Veiculo;

public class ClientePJ extends Cliente{
	private final String cnpj;
	private LocalDate dataFundacao;
	private int qtdFuncionarios;
	
	public ClientePJ(String nome, String endereco, String cnpj, LocalDate dataFundacao, List<Veiculo> listaVeiculos, int qtdFuncionarios) {
		super(nome, endereco, listaVeiculos);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdFuncionarios = qtdFuncionarios;
	}
	
	public ClientePJ(String nome, String endereco, LocalDate dataLicensa, String cnpj, LocalDate dataFundacao) {
		super(nome, endereco, dataLicensa);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public static boolean validarCNPJ(String cnpj) {
		// removemos todos os caracteres nao numericos com o regex
		cnpj = cnpj.replaceAll("[^0-9 ]", "");
				
		// verificamos o comprimento da string parametro
		if (cnpj.length() != 14) return false;
		
		// verificamos se todos os caracteres nao sao iguais
		for (int i = 0; i < 14; i++) {
			if (cnpj.charAt(0) != cnpj.charAt(i)) break;
			if (i == 13) return false;
		}
		
		// calculamos os digitos verificadores

		int primeiroVerificador = calcularDigitoVerificador(cnpj.substring(0, 12), 0);
		int segundoVerificador = calcularDigitoVerificador(cnpj.substring(0, 13), 1);
		
		// verificamos se os digitos finais passados batem com os calculados
		if (Character.getNumericValue(cnpj.charAt(12)) != primeiroVerificador) return false;
		if (Character.getNumericValue(cnpj.charAt(13)) != segundoVerificador) return false;
		
		// se passar de todas verificacoes retornamos true
		return true;
	}

	private static int calcularDigitoVerificador(String numeros, int offset) {
		// verificamos o comprimento da string parametro
		if (numeros.length() != 12 + offset) return -1;
		
		// inicalizamos a variavel "soma" que armazenara os resultados das multiplicacoes
		int soma = 0;
		
		// realizamos a primeira serie de multiplicacoes 
		// | 0 | 1 | 2 | 3 | -> String
		// | 5 | 4 | 3 | 2 | -> multiplicar
		for (int i = 0; i < 4 + offset; i++) {
			int atual = Character.getNumericValue(numeros.charAt(i));
			
			soma += atual * (5 + offset - i);
		}
		
		// e agora continuamos com a segunda serie de multiplicacoes
		// | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | -> String
		// | 9 | 8 | 7 | 6 | 5 | 4 |  3 |  2 | -> multiplicar
		for (int i = 4 + offset; i < 12 + offset; i++) {
			// calculamos a soma do digito verificador
			int atual = Character.getNumericValue(numeros.charAt(i));		
			
			// adiciona-se 4 para "zerar" i para 0
			soma += atual * (9 + 4 + offset - i);
		}			
		
		// pegamos o resto da soma / 11
		int resto = soma % 11;
		
		// caso seja 1 ou 0 (resultado de % sempre >= 0) retornamos 0
		if (resto <= 1) return 0;
		
		// caso contrario, retornamos d1 = 11 - resto
		return 11 - resto;
	}
	
	public int getQtdFuncionarios() {
		return this.qtdFuncionarios;
	}
	
	public void setQtdFuncionarios(int novaQtd) {
		this.qtdFuncionarios = novaQtd;
	}
	
	public double calcularScore() {
		int qtdVeiculos = this.getQtdVeiculos();
		int qtdFuncs = this.getQtdFuncionarios();
		
		return CalcSeguro.VALOR_BASE.getValor() * (1 + (qtdFuncs/100)) * qtdVeiculos;
	}
	
	@Override
	public String toString() {
		String ret = String.format("Nome: %s\nCNPJ: %s\nFundacao: %s\nValor seguro: R$%s", this.getNome(), this.getCnpj(), this.getDataFundacao().toString(), this.getValorSeguro());
		
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
