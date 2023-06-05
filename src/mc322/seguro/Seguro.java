package mc322.seguro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mc322.condutor.Condutor;
import mc322.seguradora.Seguradora;
import mc322.sinistro.Sinistro;

public abstract class Seguro {
	private int id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Seguradora seguradora;
	private List<Sinistro> listaSinistros;
	private List<Condutor> listaCondutores;
	private double valorMensal;
	
	public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
		this.id = gerarIdAleatorio();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.valorMensal = 0;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public LocalDate getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}
	
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
	public void setListaSinistros(List<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}
	
	public List<Condutor> getListaCondutores() {
		return listaCondutores;
	}
	
	public void setListaCondutores(List<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}
	
	public double getValorMensal() {
		return valorMensal;
	}
	
	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}
	
	public boolean desautorizarCondutor(String key) {
		for (Condutor condutor: this.getListaCondutores()) {
			if (condutor.getCpf().equals(key)) {
				this.getListaCondutores().remove(condutor);
				return true;
			}
		}
		return false;
	}
	
	public boolean autorizarCondutor(Condutor novo) {
		for (Condutor condutor: this.getListaCondutores()) {
			if (condutor.getCpf().equals(novo.getCpf())) return false;
		}
		this.getListaCondutores().add(novo);
		return true;
	}
	
	public boolean gerarSinistro(String endereco, String cpfCondutor) {
		Condutor condutor = acharCondutor(cpfCondutor);
		if (condutor == null) return false;
		
		this.getListaSinistros().add(new Sinistro(LocalDate.now(), endereco, condutor, this));		
		return true;
	}
	
	public abstract double calcularValor();
	
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
	
	protected Condutor acharCondutor(String cpf) {
		for (Condutor condutor: this.listaCondutores) {
			if (condutor.getCpf().equals(cpf)) return condutor;
		}
			
		return null;
	}
	
	public abstract String toString();
}
