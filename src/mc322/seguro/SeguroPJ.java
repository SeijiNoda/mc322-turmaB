package mc322.seguro;

import java.time.LocalDate;

import mc322.cliente.ClientePJ;
import mc322.seguradora.Seguradora;
import mc322.frota.Frota;

public class SeguroPJ extends Seguro {
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
	
	public boolean autorizarCondutor() {
		return false;
	}
	
	public boolean desautorizarCondutor() {
		return false;
	}
	
	public void gerarSinistro() {
	}
	
	public double calcularValor() {
		return -1;
	}
	
	public String toString() {
		return String.format("Seguro PJ");
	}
}