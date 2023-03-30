package mc322.sinistro;

import java.util.Random;

import mc322.seguradora.Seguradora;
import mc322.veiculo.Veiculo;
import mc322.cliente.Cliente;

public class Sinistro {
	private static int cont = 0;
	private final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	public Sinistro(String data, String endereco) {
		this.id = ++cont;
		this.data = data;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
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
		return String.format("Sinistro %d de %s ocorrido em %s.", this.getId(), this.getCliente().getNome(), this.getData());
	}
}
