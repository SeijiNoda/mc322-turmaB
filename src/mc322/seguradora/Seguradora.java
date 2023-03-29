package mc322.seguradora;

import java.util.List;
import java.util.ArrayList;

import mc322.cliente.*;
import mc322.sinistro.Sinistro;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros; 
	private List<Cliente> listaClientes;
	
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaClientes = new ArrayList<Cliente>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(List<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public boolean cadastrarCliente(Cliente novo) {
		for(Cliente cliente: this.listaClientes) {
			if (novo instanceof ClientePF && cliente instanceof ClientePF) {
				
				boolean cpfIgual = ((ClientePF) novo).getCpf().equals(((ClientePF) cliente).getCpf());
				if (cpfIgual) return false;
			} else if (novo instanceof ClientePJ && cliente instanceof ClientePJ) {
				
				boolean cnpjIgual = ((ClientePJ) novo).getCnpj().equals(((ClientePJ) cliente).getCnpj());
				if (cnpjIgual) return false;
			}
		}
		
		this.listaClientes.add(novo);
		return false;
	}
	
	public boolean removerCliente(Cliente remover) {
		for(Cliente cliente: this.listaClientes) {
			if (remover instanceof ClientePF && cliente instanceof ClientePF) {
				
				boolean cpfIgual = ((ClientePF) remover).getCpf().equals(((ClientePF) cliente).getCpf());
				if (cpfIgual) {
					this.listaClientes.remove(cliente);
					return true;
				}
			} else if (remover instanceof ClientePJ && cliente instanceof ClientePJ) {
				
				boolean cnpjIgual = ((ClientePJ) remover).getCnpj().equals(((ClientePJ) cliente).getCnpj());
				if (cnpjIgual) {
					this.listaClientes.remove(cliente);
					return true;
				}
			}
		}
		return false;
	}
}
