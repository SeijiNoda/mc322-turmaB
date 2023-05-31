package mc322.seguro;

import java.time.LocalDate;

import mc322.cliente.ClientePJ;
import mc322.condutor.Condutor;
import mc322.seguradora.Seguradora;
import mc322.frota.Frota;

public class SeguroPJ extends Seguro {
	private static final int VALOR_BASE = 10;
		
	private Frota frota;
	private ClientePJ cliente;
		
	public SeguroPJ(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, double valorMensal,
			Frota frota, ClientePJ cliente) {
		super(id, dataInicio, dataFim, seguradora, valorMensal);
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
		int anoFundacao = this.cliente.getDataFundacao()
		
		return (VALOR_BASE * (10 + (qtdFuncionarios/10)) * 
							 (1 + 1/(qtdVeiculos+2)) *
							 (1 + 1/()));
	}
	
	public String toString() {
		return String.format("Seguro PJ");
	}
}