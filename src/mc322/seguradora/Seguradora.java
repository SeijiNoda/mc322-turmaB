package mc322.seguradora;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import mc322.cliente.*;
import mc322.frota.Frota;
import mc322.seguro.*;
import mc322.sinistro.Sinistro;
import mc322.veiculo.Veiculo;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Seguro> listaSeguros; 
	private List<Cliente> listaClientes;
	
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSeguros = new ArrayList<Seguro>();
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

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
	// ============================
	// Metodos especificos
	// ============================
	
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
	
	public boolean gerarSeguro(String keyCliente, String key, LocalDate dataFim) {
		Cliente cliente = acharCliente(keyCliente);
		if (cliente == null) return false;
		Seguro novo;
		if (cliente instanceof ClientePF) {
			Veiculo veiculo = acharVeiculo((ClientePF) cliente, key);
			if (veiculo == null) return false;
			
			novo = new SeguroPF(LocalDate.now(), dataFim, this, veiculo, (ClientePF) cliente);
		} else {
			Frota frota = acharFrota((ClientePJ) cliente, key);
			if (frota == null) return false;
			
			novo = new SeguroPJ(LocalDate.now(), dataFim, this, frota, (ClientePJ) cliente);
		}	

		this.listaSeguros.add(novo);
		return true;
	}
	
	public boolean cancelarSeguro(int idSeguro) {
		for (Seguro seguro: this.listaSeguros) {
			if (seguro.getId() == idSeguro) {
				this.listaSeguros.remove(seguro);
				return true;
			}
		}
		
		return false;
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
		
//		if (novo instanceof ClientePF) this.calcularPrecoSeguroCliente(((ClientePF) novo).getCpf());
//		else if (novo instanceof ClientePJ) this.calcularPrecoSeguroCliente(((ClientePJ) novo).getCnpj());
		
		return true;
	}
	
	// Metodo para remover um cliente com um ID CPF/CNPJ key, retorna true se removeu, false senao
//	public boolean removerCliente(String key) {
//		// Pegamos uma String com o tipo do cliente da key (PF ou PJ)
//		String tipo = getTipoCliente(key);
//		if (tipo == null) return false;
//		boolean isCpf = tipo.equals("PF");
//		boolean isCnpj = tipo.equals("PJ");
//		
//		// Caso nao seja nenhum dos dois, retorna falso (pela verificacao da Main nunca acontece,
//		// mas preventivo caso seja chamado de outro lugar)
//		if (!isCpf && !isCnpj) return false;
//		
//		// Percorremos a lista de clientes da seguradora buscando o cliente com o CPF/CNPJ passado por key
//		for (Cliente cliente: this.listaClientes) {
//			if ((isCpf && ((ClientePF) cliente).getCpf().equals(key)) || (isCnpj && ((ClientePJ) cliente).getCnpj().equals(key))) {
//				
//				// Quando encontramos, removemos o cliente da lista da seguradora
//				this.listaClientes.remove(cliente);
//				
//				// Caso a lista de seguros da seguradora nao esteja vazia, temos que avaliar se 
//				// existe seguro com tal CPF/CNPJ, e se sim, remove-lo(s)
//				if (this.listaSeguros.size() > 0) {
//					
//					// Percorre a lista de seguros da seguradora
//					for (Seguro seguro: this.listaSeguros) {
//						Cliente atual = isCpf ? ((SeguroPF) seguro).getCliente() : ((SeguroPJ) seguro).getCliente();
//						if ( (isCpf && atual instanceof ClientePF && ((ClientePF) atual).getCpf().equals(key)) || ( isCnpj && atual instanceof ClientePJ && ((ClientePJ) atual).getCnpj().equals(key)) ) {
//								// Se o CPF/CNPJ do sinsitro atual da lista bater com o de key, removemos tal seguro
//								this.listaSeguros.remove(seguro);
//						} 
//					}
//				}
//				
//				return true;
//			}
//		}
//		
//		// Senao encontramos nenhum cliente apos percorrer toda a lista, retornamos false
//		return false;
//	}
//	
	public boolean removerCliente(String key) {
		Cliente cliente = acharCliente(key);
		if (cliente == null) return false;
		
		List<Seguro> remover = new ArrayList<Seguro>();
		for (Seguro seguro: this.listaSeguros) {
			if (seguro instanceof SeguroPF && cliente instanceof ClientePF) {
				if (((SeguroPF) seguro).getCliente() == cliente) remover.add(seguro);
			} else if (seguro instanceof SeguroPJ && cliente instanceof ClientePJ) {
				if (((SeguroPJ) seguro).getCliente() == cliente) remover.remove(seguro);
			}
		}
		this.listaSeguros.removeAll(remover);
		
		this.listaClientes.remove(cliente);		
		return true;
	}
	
	public boolean gerarSinistro(LocalDate data, String placa, String endereco, String keyCliente, String keyCondutor) {
		Cliente cliente = acharCliente(keyCliente);
		if (cliente == null) return false;
		
		Veiculo veiculo = acharVeiculo(cliente, placa);
		if (veiculo == null) return false;
		
		Seguro seguro = acharSeguro(keyCliente, placa);
		if (seguro == null) return false;
		
		return seguro.gerarSinistro(endereco, keyCondutor);
	}
	
	public List<Seguro> getSegurosPorCliente(String key) {
		boolean ehPF = getTipoCliente(key).equals("PF");
		Cliente cliente = acharCliente(key);
		if (cliente == null) return null;
		
		List<Seguro> lista = new ArrayList<Seguro>();
		for (Seguro seguro: this.listaSeguros) {
			if (ehPF) {
				ClientePF atual = ((SeguroPF) seguro).getCliente();
				if (atual.getCpf().equals(key)) lista.add(seguro);
			} else {
				ClientePJ atual = ((SeguroPJ) seguro).getCliente();
				if (atual.getCnpj().equals(key)) lista.add(seguro);
			}
		}
		
		return lista;
	}
	
	public List<Sinistro> getSinistrosPorCliente(String key) {
		if (acharCliente(key) == null) return null;
		
		List<Sinistro> lista = new ArrayList<Sinistro>();
		List<Seguro> seguros = this.getSegurosPorCliente(key);
		for (Seguro seguro: seguros) {
			lista.addAll(seguro.getListaSinistros());
		}
		
		return lista;
	}
	
	public double calcularReceita() {
		double receita = 0.0;
		for (Seguro seguro: this.listaSeguros) {
			receita += seguro.calcularValor();
		}
		
		return receita;
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
	
	private Seguro acharSeguro(String keyCliente, String placa) {
		for (Seguro seguro: this.listaSeguros) {
			if (seguro instanceof SeguroPF) {
				if (((SeguroPF) seguro).getCliente().getCpf().equals(keyCliente)) {
					if (((SeguroPF) seguro).getVeiculo().getPlaca().equals(placa))
						return seguro;
				}
			} else {
				if (((SeguroPJ) seguro).getCliente().getCnpj().equals(keyCliente)) {
					for (Veiculo v: ((SeguroPJ) seguro).getFrota().getListaVeiculos()) {
						if (v.getPlaca().equals(placa)) return seguro;
					}
				}
			}
		}
		
		return null;
	}
		
	private Seguro acharSeguro(String keyFrota) {
		for (Seguro seguro: this.listaSeguros) {
			if (seguro instanceof SeguroPJ) {
				if (((SeguroPJ) seguro).getFrota().getId().equals(keyFrota)) return (SeguroPJ) seguro;
			}
		}
		
		return null;
	}
	
	// Metodo auxiliar para achar um veiculo dado sua placa e o cliente de onde procurar
	private Veiculo acharVeiculo(Cliente cliente, String placa) {
		if (cliente instanceof ClientePF) {
			for (Veiculo veiculo: ((ClientePF) cliente).getListaVeiculos()) {
				if (veiculo.getPlaca().equals(placa)) return veiculo;
			}
		} else {
			for (Frota frota: ((ClientePJ) cliente).getListaFrotas()) {
				for (Veiculo veiculo: frota.getListaVeiculos()) {
					if (veiculo.getPlaca().equals(placa)) return veiculo;
				}
			}
		}
		
		return null;
	}
	
	private Frota acharFrota(ClientePJ cliente, String code) {
		for (Frota frota: cliente.getListaFrotas()) {
			if (frota.getId().equals(code)) return frota;
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
	
	// Metodo que tenta adicionar um veiculo a um cliente PF, se conseguir retorna o true, senao false
	public boolean adicionarVeiculo(Veiculo novo, String key) {
		// Buscamos o cliente com a chave key do parametro
		boolean ehPF = getTipoCliente(key).equals("PF");
		Cliente cliente = acharCliente(key);
		if (cliente == null || !ehPF) return false;
		
		
		boolean ret = ((ClientePF) cliente).cadastrarVeiculo(novo); 
		
		Seguro seguro = acharSeguro(key, novo.getPlaca());
		if (seguro == null) return false;
		seguro.calcularValor();
		
		return ret;
	}
	
	public boolean adicionarVeiculo(Veiculo novo, String keyPJ, String keyFrota) {
		boolean ehPJ = getTipoCliente(keyPJ).equals("PJ");
		Cliente cliente = acharCliente(keyPJ);
		if (cliente == null || !ehPJ) return false;
		
		// veiculo soh pode estar em uma frota
		Veiculo veiculo = acharVeiculo((ClientePJ) cliente, novo.getPlaca());
		if (veiculo != null) return false;
		
		boolean ret = ((ClientePJ) cliente).atualizarFrota(keyFrota, novo);	

		Seguro seguro = acharSeguro(keyFrota);
		if (seguro != null) seguro.calcularValor();
		
		return ret;
	}
 	
	// Metodo que tenta remover um veiculo dado sua placa de um cliente dado sua chave, retorna true em sucesso
	// e false caso nao consiga (similar ao metodo acima)
	public boolean removerVeiculo(String placa, String key) {
		Cliente cliente = acharCliente(key);
		if (cliente == null || !(cliente instanceof ClientePF)) return false;
		
		boolean ret = ((ClientePF) cliente).removerVeiculo(placa);
		Seguro seguro = acharSeguro(key, placa);
		if (seguro != null) seguro.calcularValor();	
		return ret;
	}
	
	public boolean removerVeiculo(String placa, String keyFrota, String keyPJ) {
		Cliente cliente = acharCliente(keyPJ);
		if (cliente == null || !(cliente instanceof ClientePJ)) return false;

		Veiculo veiculo = acharVeiculo(cliente, placa);
		if (veiculo == null) return false;
		
		boolean ret = ((ClientePJ) cliente).atualizarFrota(keyFrota, veiculo);
		Seguro seguro = acharSeguro(keyPJ, placa);
		if (seguro != null) seguro.calcularValor();
		return ret;
	}
	
	// Metodo para gerar um novo Sinistro dado suas informacoes nescessarias
//	public boolean gerarSinsitro(LocalDate data, String placa, String endereco, String key) {
//		// Buscamos se existe o cliente com a chave key
//		Cliente cliente = acharCliente(key);
//		if (cliente == null) return false;
//		
//		// Bucamos se este cliente tem veiculo com tal placa
//		Veiculo veiculo = acharVeiculo(cliente, placa);
//		if (veiculo == null) return false;
//		
//		// Se ambos existem, criamos o novo sinstros e inserimos ele na lista da seguradora
//		Sinistro novo = new Sinistro(data, endereco, this, veiculo, cliente);
//		this.listaSinistros.add(novo);		
//		this.calcularPrecoSeguroCliente(key);
//		return true;
//	}
//	
	public boolean removerSinistro(int id) {
		for (Seguro seguro: this.listaSeguros) {
			for (Sinistro sinistro: seguro.getListaSinistros()) {
				if (sinistro.getId() == id) {
					seguro.getListaSinistros().remove(sinistro);
					return true;
				}
			}
		}
		return false;
	}
	
	// Metodo que avalia se existe um sinistro para o cliente com CPF/CNPJ de key
//	public boolean vizualizarSinistro(String key) {
//		Cliente cliente = acharCliente(key);		
//		
//		for (Sinistro sinistro: this.listaSinistros) {
//			if (sinistro.getCliente() == cliente) return true;
//		}
//		
//		return false;
//	}
	
	// Retorna a lista de sinistros da seguradora
	public List<Sinistro> listarSinistros() {
		List<Sinistro> lista = new ArrayList<Sinistro>();
		for (Seguro seguro: this.listaSeguros) {
			lista.addAll(seguro.getListaSinistros());
		}
		
		return lista;
	}
	
	public List<Sinistro> listarSinistros(String key) {
		Cliente cliente = acharCliente(key);
		if (cliente == null) return null;
		
		for (Seguro seguro: this.listaSeguros) {
			if (cliente instanceof ClientePF) {
				if (((SeguroPF) seguro).getCliente() == cliente) {
					return seguro.getListaSinistros();
				}
			} else {
				if (((SeguroPJ) seguro).getCliente() == cliente) {
					return seguro.getListaSinistros();
				}
			}
		}
		
		return null;
	}
	
	public List<Veiculo> listarVeiculos() {
		List<Veiculo> lista = new ArrayList<Veiculo>();
		
		for (Cliente cliente: this.listaClientes) {
			if (cliente instanceof ClientePF) {
				lista.addAll(((ClientePF) cliente).getListaVeiculos());
			} else {
				for (Frota frota: ((ClientePJ) cliente).getListaFrotas()) {
					lista.addAll(frota.getListaVeiculos());
				}
			}
		}
		
		return lista;
	}
	
	public List<Veiculo> listarVeiculos(String key) {
		Cliente cliente = acharCliente(key);
		if (cliente == null) return null;
		
		if (cliente instanceof ClientePF) return ((ClientePF) acharCliente(key)).getListaVeiculos();
		else {
			List<Veiculo> lista = new ArrayList<Veiculo>();
			for (Frota frota: ((ClientePJ) cliente).getListaFrotas()) {
				lista.addAll(frota.getListaVeiculos());
			}
			return lista;
		}
	}
	
//	public double calcularPrecoSeguroCliente(String key) {
//		Cliente cliente = acharCliente(key);
//		if (cliente == null) return -1;
//		
//		int qtdSinistros = this.listaSinistros.size();
//		for (Sinistro sinistro: this.listaSinistros) {	
//			if (sinistro.getCliente() != cliente) qtdSinistros--;
//		}
//		
//		double valorSeguro = cliente.calcularScore() * (1 + qtdSinistros);
//		
//		cliente.setValorSeguro(valorSeguro);
//		return valorSeguro;
//	}
	
//	public boolean transferirSeguro(String keyOriginal, String keyNovo) {
//		Cliente original = acharCliente(keyOriginal);
//		Cliente novo = acharCliente(keyNovo);
//		if (original == null || novo == null) return false;
//		
//		List<Veiculo> lista = original.getListaVeiculos();
//		for (int i = 0; i < lista.size(); i++) {
//			Veiculo v = lista.get(i);
//			original.removerVeiculo(v.getPlaca());
//			novo.adicionarVeiculo(v);
//		}
//		this.calcularPrecoSeguroCliente(keyOriginal);
//		this.calcularPrecoSeguroCliente(keyNovo);
//		
//		return true;
//	}
	
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
