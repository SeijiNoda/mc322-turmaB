package mc322.sinistro;

import java.time.LocalDate;
import java.util.Random;

import mc322.seguradora.Seguradora;
import mc322.veiculo.Veiculo;
import mc322.cliente.Cliente;

public class Sinistro {
	private static int cont = 0;
	private final int id;
	private LocalDate data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		this.id = ++cont;
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.cliente = cliente;
		this.veiculo = veiculo;
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private int gerarIdAleatorio() {
		// utilizando da classe Random do Java para gerar um ID aleatorio para a instancia de Sinistro
		Random gerador = new Random();
		
		// settar limite maximo do numero a ser gerado
		int maximo = 100000;
		
		// gerar o numero aleatorio
		int nmrAleatorio = gerador.nextInt(maximo);
		
		// pegar hashcode do nosso numero aleatorio atraves de String.hashCode()	
		return (nmrAleatorio + "").hashCode();
	}
	
	public String toString() {
		return String.format("Sinistro de ID: %d de %s ocorrido em %s.", this.getId(), this.getCliente().getNome(), this.getData());
	}
}
