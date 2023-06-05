package mc322.frota;

import java.util.ArrayList;
import java.util.List;

import mc322.veiculo.Veiculo;

public class Frota {
	private String id;
	private List<Veiculo> listaVeiculos;
	
	public Frota(String id) {
		this.id = id;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	private Veiculo acharVeiculo(String key) {
		for (Veiculo v: this.listaVeiculos) {
			if (v.getPlaca().equals(key)) return v;
		}
		return null;
	}
	
	public boolean adicionarVeiculo(Veiculo novo) { 
		if (acharVeiculo(novo.getPlaca()) == null) return false;
		
		this.listaVeiculos.add(novo);
		return true;
	}
	
	public boolean removerVeiculo(String key) {
		Veiculo v = acharVeiculo(key);
		if (v == null) return false;
		
		this.listaVeiculos.remove(v);
		return true;
	}
	
	public String toString() {
		return String.format("FROTA %s: %d veiculos.", this.id, this.listaVeiculos.size());
	}
}
