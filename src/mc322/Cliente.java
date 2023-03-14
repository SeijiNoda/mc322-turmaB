package mc322;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public boolean validarCPF(String cpf) {
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
	
	private int calcularDigitoVerificador(String numeros) {
		// verificamos o comprimento da string parametro
		if (numeros.length() != 9) return -1;
		
		// inicalizamos a variavel "soma" que armazenara os resultados das multiplicacoes
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			// calculamos a soma do digito verificador
			int atual = Character.getNumericValue(numeros.charAt(i));		
			
			soma += atual * (10 - i);
		}			
		
		System.out.println(soma);
		
		// pegamos o resto da soma / 11
		int resto = soma % 11;
		
		// caso seja 1 ou 0 (resultado de % sempre >= 0) retornamos 0
		if (resto <= 1) return 0;
		
		// caso contrario, retornamos d1 = 11 - resto
		return 11 - resto;
	}
	
	public String toString() {
		// usamos String.format() para concatenar a string de retorno
		return String.format("Nome: %s\nCPF: %s\nNascimento: %s\nIdade %d\nEndereco: %s", 
							 this.getNome(), this.getCpf(), this.getDataNascimento(), this.getIdade(), this.getEndereco());
	}
}
