package mc322.seguradora;

import java.util.List;
import java.time.LocalDate;
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
	
	// Metodo para cadastrar um novo cliente
	public boolean cadastrarCliente(Cliente novo) {
		// percorremos o array de clientes para verificar se o CPF/CNPJ nao eh repetido
		for(Cliente cliente: this.listaClientes) {
			// verificamos se o CPF eh repetido e depois se o CNPJ eh
			if (novo instanceof ClientePF && cliente instanceof ClientePF) {
				
				boolean cpfIgual = ((ClientePF) novo).getCpf().equals(((ClientePF) cliente).getCpf());
				if (cpfIgual) return false;
			} else if (novo instanceof ClientePJ && cliente instanceof ClientePJ) {
				
				boolean cnpjIgual = ((ClientePJ) novo).getCnpj().equals(((ClientePJ) cliente).getCnpj());
				if (cnpjIgual) return false;
			}
		}
		
		// Se o CPF/CNPJ do novo cliente nao for repetido, adicionamos ele na lista de clientes da seguradora
		this.listaClientes.add(novo);
		return true;
	}
	
	// Metodo para remover um cliente com um ID CPF/CNPJ key, retorna true se removeu, false senao
	public boolean removerCliente(String key) {
		// Pegamos uma String com o tipo do cliente da key (PF ou PJ)
		String tipo = getTipoCliente(key);
		if (tipo == null) return false;
		boolean isCpf = tipo.equals("PF");
		boolean isCnpj = tipo.equals("PJ");
		
		// Caso nao seja nenhum dos dois, retorna falso (pela verificacao da Main nunca acontece,
		// mas preventivo caso seja chamado de outro lugar)
		if (!isCpf && !isCnpj) return false;
		
		// Percorremos a lista de clientes da seguradora buscando o cliente com o CPF/CNPJ passado por key
		for (Cliente cliente: this.listaClientes) {
			if ((isCpf && ((ClientePF) cliente).getCpf().equals(key)) || (isCnpj && ((ClientePJ) cliente).getCnpj().equals(key))) {
				
				// Quando encontramos, removemos o cliente da lista da seguradora
				this.listaClientes.remove(cliente);
				
				// Caso a lista de sinistros da seguradora nao esteja vazia, temos que avaliar se 
				// existe sinistro com tal CPF/CNPJ, e se sim, remove-lo(s)
				if (this.getListaSinistros().size() > 0) {
					
					// Percorre a lista de sinistras da segudora
					for (Sinistro sinistro: this.listaSinistros) {
						Cliente atual = sinistro.getCliente();
						if ( (isCpf && atual instanceof ClientePF && ((ClientePF) atual).getCpf().equals(key)) || ( isCnpj && atual instanceof ClientePJ && ((ClientePJ) atual).getCnpj().equals(key)) ) {
								// Se o CPF/CNPJ do sinsitro atual da lista bater com o de key, removemos tal sinistro
								this.listaSinistros.remove(sinistro);
						} 
					}
				}
				
				return true;
			}
		}
		
		// Senao encontramos nenhum cliente apos percorrer toda a lista, retornamos false
		return false;
	}
	
	// Funcao que retorna uma lista de clientes com o tipo especificado no parametro, se este nao for
	// nem "PF" nem "PJ", retorna todos os clientes
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
	
	// Metodo auxiliar para achar um cliente dado seu CPF/CNPJ
	private Cliente acharCliente(String key) {
		// String com o tipo do cliente: "PF" ou "PJ"
		String tipo = getTipoCliente(key);
		if (tipo == null) return null;
		
		for (Cliente cliente: this.listaClientes) {
			if ( (tipo.equals("PF") && cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(key)) || 
				 (tipo.equals("PJ") && cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(key)) ) {
				return cliente;
			} 
		}
		
		return null;
	}
	
	// Metodo auxiliar para achar um veiculo dado sua placa e o cliente de onde procurar
	private Veiculo acharVeiculo(Cliente cliente, String placa) {
		for (Veiculo veiculo: cliente.getListaVeiculos()) {
			if (veiculo.getPlaca().equals(placa)) return veiculo;
		}
		
		return null;
	}
	
	// Metodo auxiliar que retorna uma String "PF" caso key tenha o tamanho de um CPF, "PJ"
	// se tiver tamanho de CNPJ, e null caso nao seja nenhum ou outro 
	private String getTipoCliente(String key) {
		key = key.replaceAll("[^0-9 ]", "");
		int keySize = key.length();
		boolean isCpf = keySize == 11;
		boolean isCnpj = keySize == 14;
		
		if (!isCpf && !isCnpj) return null;
		else if (isCpf) return "PF";
		else return "PJ";
	}
	
	// Metodo que tenta adicionar um veiculo a um cliente, se conseguir retorna o true, senao false
	public boolean adicionarVeiculo(Veiculo novo, String key) {
		// Buscamos o cliente com a chave key do parametro
		Cliente cliente = acharCliente(key);
		if (cliente == null) return false;
		
		// Usamos o metodo booleano da classe Cliente para adicionar o novo veiculo ao cliente encontrado
		return cliente.adicionarVeiculo(novo);
	}
	
	// Metodo que tenta remover um veiculo dado sua placa de um cliente dado sua chave, retorna true em sucesso
	// e false caso nao consiga (similar ao metodo acima)
	public boolean removerVeiculo(String placa, String key) {
		Cliente cliente = acharCliente(key);
		if (cliente == null) return false;
		
		return cliente.removerVeiculo(placa);
	}
	
	// Metodo para gerar um novo Sinistro dado suas informacoes nescessarias
	public boolean gerarSinsitro(LocalDate data, String placa, String endereco, String key) {
		// Buscamos se existe o cliente com a chave key
		Cliente cliente = acharCliente(key);
		if (cliente == null) return false;
		
		// Bucamos se este cliente tem veiculo com tal placa
		Veiculo veiculo = acharVeiculo(cliente, placa);
		if (veiculo == null) return false;
		
		// Se ambos existem, criamos o novo sinstros e inserimos ele na lista da seguradora
		Sinistro novo = new Sinistro(data, endereco, this, veiculo, cliente);
		this.listaSinistros.add(novo);		
		return true;
	}
	
	// Metodo que avalia se existe um sinistro para o cliente com CPF/CNPJ de key
	public boolean vizualizarSinistro(String key) {
		Cliente cliente = acharCliente(key);		
		
		for (Sinistro sinistro: this.listaSinistros) {
			if (sinistro.getCliente() == cliente) return true;
		}
		
		return false;
	}
	
	// Retorna a lista de sinistros da seguradora
	public List<Sinistro> listarSinistros() {
		return this.getListaSinistros();
	}
	
	// Este toString() formata os clientes da seguradora caso esta tenha algum, alem de seus outros atributos
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
