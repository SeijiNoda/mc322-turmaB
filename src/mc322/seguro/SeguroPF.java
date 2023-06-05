package mc322.seguro;

import java.time.LocalDate;

import mc322.cliente.ClientePF;
import mc322.condutor.Condutor;
import mc322.seguradora.Seguradora;
import mc322.veiculo.Veiculo;

public class SeguroPF extends Seguro {
	private static final int VALOR_BASE = 10;
	private static final double FATOR_IDADE_1 = 1.25;
	private static final double FATOR_IDADE_2 = 1.0;
	private static final double FATOR_IDADE_3 = 1.5;
	
	private Veiculo veiculo;
	private ClientePF cliente;
		
	public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
		super(dataInicio, dataFim, seguradora);
		this.veiculo = veiculo;
		this.cliente = cliente;
		calcularValor();
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public ClientePF getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePF cliente) {
		this.cliente = cliente;
	}
	
	public double calcularValor() {
		int idade = this.cliente.getIdade();
		double fatorIdade = FATOR_IDADE_1;
		if (idade > 30) {
			if (idade > 60) fatorIdade = FATOR_IDADE_3;
			else fatorIdade = FATOR_IDADE_2;
		}
		
		int qtdSinistrosCondutor = 0;
		for (Condutor condutor: this.getListaCondutores()) {
			qtdSinistrosCondutor += condutor.getListaSinistros().size();
		}
		
		int qtdVeiculos = this.cliente.getListaVeiculos().size();
		
		int qtdSinistrosCliente = this.getListaSinistros().size();
 		
		
		double novoValor = (VALOR_BASE * fatorIdade * (1 + 1/(qtdVeiculos + 2)) * 
						  				  (2 + (qtdSinistrosCliente/10)) * 
								  		  (5 + (qtdSinistrosCondutor/10)));
		
		this.setValorMensal(novoValor);
		return novoValor;
	}
	
	public String toString() {
		return String.format("\nSeguro %d:\n%s - %s\n%s\nR$ %f/mes\n", this.getId(), this.cliente.getNome(), this.cliente.getCpf(), this.veiculo.toString(), this.getValorMensal());
	}
}
