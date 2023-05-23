package mc322.seguro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	
	
	public Seguro(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, double valorMensal) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.valorMensal = valorMensal;
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
	
	public abstract boolean desautorizarCondutor();
	
	public abstract boolean autorizarCondutor();
	
	public abstract void gerarSinistro();
	
	public abstract double calcularValor();
	
	public abstract String toString();
}
