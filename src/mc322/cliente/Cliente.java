package mc322.cliente;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import mc322.veiculo.Veiculo;

public class Cliente {
	private String nome;
	private String endereco;
	private Date dataLicensa;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private List<Veiculo> listaVeiculos;
	
	public Cliente(String nome, String endereco, Date dataLicensa, String educacao, String genero, String classeEconomica) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
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

	public Date getDataLicensa() {
		return dataLicensa;
	}

	public void setDataLicensa(Date dataLicensa) {
		this.dataLicensa = dataLicensa;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public String toString() {
		// usamos String.format() para concatenar a string de retorno
		return String.format("Nome: %s\nEndereco: %s", 
							 this.getNome(), this.getEndereco());
	}
}
