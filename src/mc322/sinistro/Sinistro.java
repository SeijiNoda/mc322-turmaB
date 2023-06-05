package mc322.sinistro;

import java.time.LocalDate;
import java.util.Random;

import mc322.condutor.Condutor;
import mc322.seguro.Seguro;

public class Sinistro {
	private final int id;
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
		this.id = gerarIdAleatorio();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
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
	
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
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
		return String.format("Sinistro de ID: %d ocorrido em %s.", this.getId(), this.getData());
	}
}
