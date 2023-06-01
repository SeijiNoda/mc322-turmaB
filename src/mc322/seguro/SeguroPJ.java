package mc322.seguro;

import java.time.LocalDate;
import java.time.Period;

import mc322.cliente.ClientePJ;
import mc322.condutor.Condutor;
import mc322.seguradora.Seguradora;
import mc322.frota.Frota;

public class SeguroPJ extends Seguro {
	private static final int VALOR_BASE = 10;
		
	private Frota frota;
	private ClientePJ cliente;
		
	public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
		super(dataInicio, dataFim, seguradora);
		this.frota = frota;
		this.cliente = cliente;
	}

	public Frota getVeiculo() {
		return frota;
	}
	
	public void setVeiculo(Frota veiculo) {
		this.frota = veiculo;
	}
	
	public ClientePJ getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePJ cliente) {
		this.cliente = cliente;
	}
	
	public void gerarSinistro() {
	}
	
	public double calcularValor() {
		int qtdFuncionarios = this.cliente.getQtdFuncionarios();
		int qtdVeiculos = this.frota.getListaVeiculos().size();
		int anosPosFundacao = Period.between(LocalDate.now(), this.cliente.getDataFundacao()).getYears();
		int quantidadeSinistrosCliente = this.getListaSinistros().size();
		int quantidadeSinistrosCondutores = 0;
		for (Condutor condutor: this.getListaCondutores()) {
			quantidadeSinistrosCondutores += condutor.getListaSinistros().size();
		}
		
		return (VALOR_BASE * (10 + (qtdFuncionarios/10)) * 
							 (1 + 1/(qtdVeiculos+2)) *
							 (1 + 1/(anosPosFundacao+2)) *
							 (2 + (quantidadeSinistrosCliente/10)) * 
							 (5 + (quantidadeSinistrosCondutores/10)));
	}
	
	public String toString() {
		return String.format("Seguro PJ");
	}
}