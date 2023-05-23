package mc322.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mc322.frota.Frota;
import mc322.veiculo.Veiculo;

public class ClientePJ extends Cliente{
	private final String cnpj;
	private LocalDate dataFundacao;
	private List<Frota> listaFrotas;
	
	public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, LocalDate dataFundacao) {
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.listaFrotas = new ArrayList<Frota>();
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
	
	public List<Frota> getListaFrotas() {
		return listaFrotas;
	}

	public void setListaFrotas(List<Frota> listaFrotas) {
		this.listaFrotas = listaFrotas;
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
	
	public boolean cadastrarFrota(String key) {
		for (Frota frota: this.listaFrotas) {
			if (frota.getId().equals(key)) return false;
		}
		this.listaFrotas.add(new Frota(key));
		return true;
	}
	
	public boolean atualizarFrota(String key) {
		for (Frota frota: this.listaFrotas) {
			if (frota.getId().equals(key)) {
				this.listaFrotas.remove(frota);
				return true;
			}
		}
		return false;
	}
	
	public boolean atualizarFrota(String key, Veiculo v) {
		for (Frota frota: this.listaFrotas) {
			if (frota.getId().equals(key)) {
				for (Veiculo vAtual: frota.getListaVeiculos()) {
					if (vAtual.getPlaca().equals(v.getPlaca())) {
						frota.removerVeiculo(v.getPlaca());
						return true;
					}
				}
				frota.adicionarVeiculos(v);
				return true;
			}
		}
		return false;
	}
	
	public List<Veiculo> getVeiculosPorFrota(String key) {
		List<Veiculo> ret = null;
		for (Frota frota: this.listaFrotas) {
			if (frota.getId().equals(key)) {
				ret = frota.getListaVeiculos();
				break;
			}
		}
		return ret;
	}
	
	@Override
 	public String toString() {
		String ret = String.format("Nome: %s\nCNPJ: %s\nFundacao: %s", this.getNome(), this.getCnpj(), this.getDataFundacao().toString());
		
		int nmrVeiculos = this.listaFrotas.size();
		if (nmrVeiculos > 0) {
			ret += "\nFrota: [\n";
			for (Frota frota: this.listaFrotas) {
				ret += frota.toString();
				if (--nmrVeiculos > 0) {
					ret += ",\n";
				} else ret += "\n";
			}
			ret += "]";
		}
		
		return ret;
	}
}
