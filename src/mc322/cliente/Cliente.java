package mc322.cliente;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import mc322.veiculo.Veiculo;

public abstract class Cliente {
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	private double valorSeguro;
	
	public Cliente(String nome, String endereco, List<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
		this.valorSeguro = 0;
	}
	
	public Cliente(String nome, String endereco, LocalDate dataLicensa) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public int getQtdVeiculos() {
		return listaVeiculos.size();
	}
	
	public double getValorSeguro() {
		return this.valorSeguro;
	}
	
	public void setValorSeguro(double valor) {
		this.valorSeguro = valor;
	}
 	
	// Metodo boolean para adicionar um novo veiculo na lista de veiculos do cliente
	public boolean adicionarVeiculo(Veiculo novo) {
		// Percorre a lista de veiculos para ver se a placa do novo veiculo nao eh repetida
		for (Veiculo veiculo: this.listaVeiculos) {
			if (veiculo.getPlaca().equals(novo.getPlaca())) return false;
		}
		
		// Se nao for, adiciona o novo veiculo a lisa
		this.listaVeiculos.add(novo);
		return true;
	}
	
	// Metodo boolean para remover um veiculo dado sua placa
	public boolean removerVeiculo(String placa) {
		// Percorremos a lista de veiculos ateh achar um veiculo com a placa desejada, e entao o removemos
		for (Veiculo veiculo: this.listaVeiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				this.listaVeiculos.remove(veiculo);
				return true;
			}
		}
		
		// Senao encontrarmos, retornamos false
		return false;
	}
	
	public abstract double calcularScore();
	
	public String toString() {
		// usamos String.format() para concatenar a string de retorno
		return String.format("Nome: %s\nEndereco: %s\nSeguro: R$%s", 
							 this.getNome(), this.getEndereco(), this.getValorSeguro());
	}
}
