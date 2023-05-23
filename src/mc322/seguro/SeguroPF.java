package mc322.seguro;

import java.time.LocalDate;

import mc322.cliente.ClientePF;
import mc322.seguradora.Seguradora;
import mc322.veiculo.Veiculo;

public class SeguroPF extends Seguro {
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
		return String.format("Seguro PF");
	}
}
