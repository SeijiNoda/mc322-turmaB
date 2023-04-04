package mc322.seguradora;

import java.util.List;
import java.util.ArrayList;

import mc322.cliente.*;
import mc322.sinistro.Sinistro;
import mc322.veiculo.Veiculo;

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
		return true;
	}
	
	public boolean removerCliente(String key) {
		key = key.replaceAll("[^0-9 ]", "");
		int keySize = key.length();
		boolean isCpf = keySize == 11;
		boolean isCnpj = keySize == 14;
		
		if (!isCpf && !isCnpj) return false;
		
		for (Cliente cliente: this.listaClientes) {
			if ((isCpf && ((ClientePF) cliente).getCpf().equals(key)) || (isCnpj && ((ClientePJ) cliente).getCnpj().equals(key))) {
				this.listaClientes.remove(cliente);
				return true;
			}
		}
		
		return false;
	}
	
	public List<Cliente> listarClientes(String tipoCliente) {
		tipoCliente = tipoCliente.toUpperCase();
		List<Cliente> ret = new ArrayList<Cliente>();
		if (tipoCliente.equals("PF")) {
			for (Cliente cliente: this.listaClientes) {
				if (cliente instanceof ClientePF) ret.add(cliente);
			}
		}
		else if (tipoCliente.equals("PJ")) {
			for (Cliente cliente: this.listaClientes) {
				if (cliente instanceof ClientePJ) ret.add(cliente);
			}
		} else {
			ret = this.getListaClientes();
		}
		
		return ret;
	}
	
	private Cliente acharCliente(String tipo, String key) {
		for (Cliente cliente: this.listaClientes) {
			if ( (tipo.equals("PF") && cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(key)) || 
				 (tipo.equals("PJ") && cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(key)) ) {
				return cliente;
			} 
		}
		
		return null;
	}
	
	public boolean adicionarVeiculo(Veiculo novo, String tipoCliente, String key) {
		Cliente cliente = acharCliente(tipoCliente, key);
		if (cliente == null) return false;
		
		return cliente.adicionarVeiculo(novo);
	}
	
	public boolean removerVeiculo(String placa, String tipoCliente, String key) {
		Cliente cliente = acharCliente(tipoCliente, key);
		if (cliente == null) return false;
		
		return cliente.removerVeiculo(placa);
	}
	
	public boolean gerarSinsitro() {
		return false;
	}
	
	public boolean vizualizarSinistro(String cliente) {
		return false;
	}
	
	public List<Sinistro> listarSinistros() {
		return this.getListaSinistros();
	}
	
	public String toString() {
		String ret = String.format("%s - %s\n%s", this.getNome(), this.getTelefone(), this.getEndereco());
		
		int nmrClientes = this.getListaClientes().size();
		if (nmrClientes > 0) {
			ret += "\nClientes: [\n";
			for (Cliente cliente: this.getListaClientes()) {
				ret += cliente.toString();
				if (--nmrClientes > 0) {
					ret += ",\n";
				} else ret += "\n";
			}
			ret += "]";
		}
		
		return ret;
	}
}
