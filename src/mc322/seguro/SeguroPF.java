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
		
	public SeguroPF(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, double valorMensal,
			Veiculo veiculo, ClientePF cliente) {
		super(id, dataInicio, dataFim, seguradora, valorMensal);
		this.veiculo = veiculo;
		this.cliente = cliente;
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
	
	public void gerarSinistro() {
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
 		
		return (VALOR_BASE * fatorIdade * (1 + 1/(qtdVeiculos + 2)) * 
						  				  (2 + (qtdSinistrosCliente/10)) * 
								  		  (5 + (qtdSinistrosCondutor/10)));
	}
	
	public String toString() {
		return String.format("Seguro PF");
	}
}
