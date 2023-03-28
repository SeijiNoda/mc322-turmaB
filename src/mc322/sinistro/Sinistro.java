package mc322.sinistro;

import java.util.Random;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	public Sinistro(String data, String endereco) {
		this.id = gerarID();
		this.data = data;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	private int gerarID() {
		// utilizando da classe Random do Java para gerar um ID aleatorio para a instancia de Sinistro
		Random gerador = new Random();
		
		// settar limite maximo do numero a ser gerado
		int maximo = 100000;
		
		// gerar o numero aleatorio
		int nmrAleatorio = gerador.nextInt(maximo);
		
		// pegar hashcode do nosso numero aleatorio atraves de String.hashCode()	
		return (nmrAleatorio + "").hashCode();
	}
}
