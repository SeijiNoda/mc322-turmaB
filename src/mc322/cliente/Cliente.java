package mc322.cliente;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import mc322.veiculo.Veiculo;

public class Cliente {
	private String nome;
	private String endereco;
	private LocalDate dataLicensa;
	private List<Veiculo> listaVeiculos;
	
	public Cliente(String nome, String endereco, LocalDate dataLicensa, List<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.listaVeiculos = listaVeiculos;
	}
	
	public Cliente(String nome, String endereco, LocalDate dataLicensa) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
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

	public LocalDate getDataLicensa() {
		return dataLicensa;
	}

	public void setDataLicensa(LocalDate dataLicensa) {
		this.dataLicensa = dataLicensa;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public boolean adicionarVeiculo(Veiculo novo) {
		for (Veiculo veiculo: this.listaVeiculos) {
			if (veiculo.getPlaca().equals(novo.getPlaca())) return false;
		}
		
		this.listaVeiculos.add(novo);
		return true;
	}
	
	public boolean removerVeiculo(String placa) {
		for (Veiculo veiculo: this.listaVeiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				this.listaVeiculos.remove(veiculo);
				return true;
			}
		}
		
		return false;
	}
	
	public String toString() {
		// usamos String.format() para concatenar a string de retorno
		return String.format("Nome: %s\nEndereco: %s", 
							 this.getNome(), this.getEndereco());
	}
}
