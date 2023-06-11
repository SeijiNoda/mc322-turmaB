package mc322;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mc322.cliente.Cliente;
import mc322.cliente.ClientePF;
import mc322.cliente.ClientePJ;
import mc322.condutor.Condutor;
import mc322.leitor.InputReader;
import mc322.menu.MenuOperacoes;
import mc322.menu.SubmenuOperacoes;
import mc322.seguradora.Seguradora;
import mc322.seguro.SeguroPF;
import mc322.sinistro.Sinistro;
import mc322.veiculo.Veiculo;

public class Main {
	private static void printError(String msg) {
		System.out.println("\u001B[31m" + msg + "\u001B[0m");
	}
	
	private static void exibirMenu() {
		MenuOperacoes[] menu = MenuOperacoes.values();
		System.out.println("Menu Principal: ");
		
		for (MenuOperacoes op: menu) {
			System.out.println(op.getId() + " - " + op.getOperacao());
		}
	}
	
	private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOperacoes[] submenu = op.getSubmenu();
		System.out.println(op.getOperacao());
		for (int i=0; i<submenu.length; i++) {
			System.out.println(op.getId() + "." + (i+1) + " - " + submenu[i].getDescricao());
		}
	}
	
	private static MenuOperacoes lerOperacaoMenu() {
		int opId = -1;
		MenuOperacoes[] opArray = MenuOperacoes.values();
		do {
			exibirMenu();
			try {
				opId = Integer.parseInt(InputReader.lerString("Digite uma opcao: "));
			} catch(NumberFormatException e) {
				printError("ERRO: Por favor digite apenas inteiros do indice.");
			}
		} while (opId < 0 || opId > opArray.length - 1);
		for (MenuOperacoes op: opArray) {
			if (op.getId() == opId) return op;
		}
		
		// nunca acontece
		return null;
	}
	
	private static SubmenuOperacoes lerOperacaoSubmenu(MenuOperacoes op) {
		int opId = -1;
		SubmenuOperacoes[] opArray = op.getSubmenu();
		do {
			exibirSubmenu(op);
			try {
				opId = Integer.parseInt(InputReader.lerString("Digite uma opcao: "))-1;
			} catch(NumberFormatException e) {
				printError("ERRO: Por favor digite apenas inteiros do indice.");
			}
		} while (opId < 0 || opId > opArray.length - 1);
		return opArray[opId];
	}
	
	private static void executarOperacaoMenu(MenuOperacoes op) {
		switch (op) {
			case SAIR: break;	
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				rodarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				cadastrarSinistro();
				break;
			case CALCULAR_RECEITA:
				calcularReceita();
				break;
		}
	}
	
	private static void executarOperacaoSubmenu(SubmenuOperacoes op) {
		switch(op) {
		case VOLTAR:
			break;
		case CADASTRAR_CLIENTE:
			cadastrarCliente();
			break;
		case CADASTRAR_VEICULO:
			cadastrarVeiculo();
			break;
		case CADASTRAR_SEGURADORA:
			cadastrarSeguradora();
			break;
		case CADASTRAR_SEGURO:
			cadastrarSeguro();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			break;
		case LISTAR_SINISTROS_SEGURADORA:
			listarSinistros();
			break;
		case LISTAR_SINISTROS_CLIENTE: {
			String key = "";
			String tipo = InputReader.lerTipoCliente();
			
			if (tipo.equals("PF")) key = InputReader.lerCPF();
			else key = InputReader.lerCNPJ();
		
			listarSinistros(key);
			break;
		}
		case LISTAR_VEICULOS_SEGURADORA:
			listarVeiculos();
			break;
		case LISTAR_VEICULOS_CLIENTE: {
			String key = "";
			String tipo = InputReader.lerTipoCliente();
			
			if (tipo.equals("PF")) key = InputReader.lerCPF();
			else key = InputReader.lerCNPJ();
		
			listarVeiculos(key);
			break;
		}
		case LISTAR_SEGURADORAS:
			listarSeguradoras();
			break;
		case EXCLUIR_CLIENTE:
			excluirCliente();
			break;
		case EXCLUIR_VEICULO:
			excluirVeiculo();
			break;
		case EXCLUIR_SINISTRO:
			excluirSinistro();
			break;
		case EXCLUIR_SEGURO:
			excluirSeguro();
			break;
		}
	}
	
	private static void rodarSubmenu(MenuOperacoes op) {
		SubmenuOperacoes subOp;
		do {
			subOp = lerOperacaoSubmenu(op);
			executarOperacaoSubmenu(subOp);
		} while(subOp != SubmenuOperacoes.VOLTAR);
	}
	
	private static List<Seguradora> seguradoras = new ArrayList<Seguradora>();
	
	private static int existeSeguradora(String nome) {
		for (int i = 0; i < seguradoras.size(); i++) {
			if (seguradoras.get(i).getNome().equals(nome)) return i;
		}
		
		return -1;
	}
	
	private static boolean cadastrarCliente() {
		if (seguradoras.get(0) == null) {
			System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
			return false;
		}
		String seguradoraStr = "";
		int segId = -1;
		while (segId < 0) {
			seguradoraStr = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(seguradoraStr);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		String nome = InputReader.lerNome();
		
		String endereco = InputReader.lerString("Endereco: ");
		
		String telefone = InputReader.lerString("Telefone: ");
		
		String email = InputReader.lerEmail();
		
		String tipo = InputReader.lerTipoCliente();
		
		if (tipo.equals("PF")) {
			String educacao = InputReader.lerString("Educacao: ");
			
			String genero = InputReader.lerString("Genero: ");

			String cpf = InputReader.lerCPF();
;									
			LocalDate dataNascimento = InputReader.lerData("Aniversario: [dd/mm/yyyy] ");
		
			ClientePF novoCliente = new ClientePF(nome, telefone, endereco, email, cpf, dataNascimento, educacao, genero);
			if (seguradora.cadastrarCliente(novoCliente)) {
				System.out.println("\nMENSAGEM: " + nome + " cadastrado com sucesso!\n");
				return true;
			} else {
				System.out.println("\nMENSAGEM: Jah existe cliente com CPF " + cpf + ".\n");
				return false;
			}
		} else {
			String cnpj = InputReader.lerCNPJ();
			
			LocalDate dataFundacao = InputReader.lerData("Data de fundacao: [dd/mm/yyyy]");
			
			ClientePJ novoCliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao);
			if (seguradora.cadastrarCliente(novoCliente)) {
				System.out.println("\nMENSAGEM: " + nome + " cadastrado com sucesso!\n");
				return true;
			} else {
				System.out.println("\nMENSAGEM: Jah existe cliente com CNPJ " + cnpj + ".\n");
				return false;
			}
		}
	}
	
	private static boolean cadastrarVeiculo() {
		if (seguradoras.get(0) == null) {
			System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
			return false;
		}
		String seguradoraStr = "";
		int segId = -1;
		while (segId < 0) {
			seguradoraStr = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(seguradoraStr);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		String tipo = InputReader.lerTipoCliente();
		
		String placa = InputReader.lerPlaca();
		
		String marca = InputReader.lerString("Marca: ");
		
		String modelo = InputReader.lerString("Modelo: ");
		
		int anoFabricacao = Integer.parseInt(InputReader.lerString("Ano de fabricacao: "));
		
		Veiculo novo = new Veiculo(placa, marca, modelo, anoFabricacao);
		
		String key = "";
		String frota = "";
		boolean ret = false;
		if (tipo.equals("PF")) {
			key = InputReader.lerCPF("CPF do cliente ao qual adicionar: ");
			ret = seguradora.adicionarVeiculo(novo, key);
		}
		else {
			key = InputReader.lerCNPJ("CNPJ do cliente ao qual adicionar: ");
			frota = InputReader.lerString("ID da frota ao qual adicionar: ");
			ret = seguradora.adicionarVeiculo(novo, key, frota);
		}
		
		// Verificamos o resultado do metodo de adicionar e informamos o usuario de acordo
		if (ret) System.out.println("\nMENSAGEM: Veiculo novo adicionado com sucesso!\n");
		else System.out.println("\nMENSAGEM: Cliente jah possue veiculo com placa: " + placa +".\n");
		
		return ret;
	}
	
	private static void cadastrarSeguradora() {
		String nome = "";
		int segId = 1;
		while (segId >= 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		
		String telefone = InputReader.lerString("Telefone: ");
		
		String email = InputReader.lerEmail();
		
		String endereco = InputReader.lerString("Endereco: ");
		
		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
		seguradoras.add(seguradora);
	}
	
	private static boolean cadastrarSinistro() {
		String key = "";
		
		String tipo = InputReader.lerTipoCliente();
		
		if (tipo.equals("PF")) 
			key = InputReader.lerCPF();
		else
			key = InputReader.lerCNPJ();
			
		String placa = InputReader.lerPlaca("Placa do veiculo do acidente: ");
		
		LocalDate data = InputReader.lerData("Data do acidente: [dd/mm/yyyy] ");
		
		String endereco = InputReader.lerString("Endereco do acidente: ");
		
		String nome = "";
		int segId = -1;
		while (segId < 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		String keyCondutor = InputReader.lerCPF("CPF do condutor: ");
		
		// Verificamos o resultado de nossa tentativa de cadastro e informamos o usuario de acordo com tal resutlado
		if (seguradora.gerarSinistro(data, placa, endereco, key, keyCondutor)) {
			System.out.println("\nMENSAGEM: Sinistro registrado com sucesso!\n");
			return true;
		}
		System.out.println("\nMENSAGEM: Falha ao cadastrar sinistro.\nVerifique os dados e tente novamente.\n");
		return false;
	}
	
	private static boolean cadastrarSeguro() {
		if (seguradoras.get(0) == null) {
			System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
			return false;
		}
		
		String seguradoraStr = "";
		int segId = -1;
		while (segId < 0) {
			seguradoraStr = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(seguradoraStr);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		String tipo = InputReader.lerTipoCliente();
		
		if (tipo.equals("PF")) {
			String cpf = InputReader.lerCPF();

			String placa = InputReader.lerPlaca("Placa do veiculo a ser segurado: ");
			
			if (seguradora.gerarSeguro(cpf, placa, LocalDate.now().plusYears(1))) {
				System.out.println("\nMENSAGEM: Seguro de " + cpf + " para o veiculo de placa " + placa + " criado com sucesso!\n");
				return true;
			} else {
				printError("\nMENSAGEM: Erro ao criar seguro, verifique os dados e tente novamente.\n");
				return false;
			}
		} else {
			String cnpj = InputReader.lerCNPJ();
			
			String codeFrota = InputReader.lerString("Codigo da frota a ser segurada: ");
			
			if (seguradora.gerarSeguro(cnpj, codeFrota, LocalDate.now().plusYears(1))) {
				System.out.println("\nMENSAGEM: Seguro de " + cnpj  + " para a frota " + codeFrota + " criado com sucesso!\n");
				return true;
			} else {
				printError("\nMENSAGEM: Erro ao criar seguro, verifique os dados e tente novamente.\n");
				return false;
			}
		}
	}
	
	private static void listarClientes() {
		if (seguradoras.get(0) == null) System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
		
		String tipo = InputReader.lerTipoCliente();
				
		for (Seguradora seguradora: seguradoras) {
			System.out.println("\n" + seguradora.getNome().toUpperCase() + ":");
			
			List<Cliente> lista = seguradora.listarClientes(tipo);
			if (lista.size() == 0) {
				System.out.println("\nMENSAGEM: Lista de clientes vazia!\n");
			} else {
				for (Cliente cliente: lista) {
					System.out.println(cliente.toString() + "\n");
				}
			}
		}
	}
	
	private static void listarSinistros() {
		if (seguradoras.get(0) == null) System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
		for (Seguradora seguradora: seguradoras) {
			System.out.println("\n" + seguradora.getNome().toUpperCase() + ":");

			List<Sinistro> lista = seguradora.listarSinistros();
			if (lista.size() == 0) {
				System.out.println("\nMENSAGEM: Lista de sinistros vazia!\n");
			} else {
				for (Sinistro sinistro: lista) {
					System.out.println(sinistro.toString() + "\n");
				}
			}
		}
	}
	
	private static void listarSinistros(String key) {
		if (seguradoras.get(0) == null) System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
		
		for (Seguradora seguradora: seguradoras) {
			System.out.println("\n" + seguradora.getNome().toUpperCase() + ":");

			List<Sinistro> lista = seguradora.getSinistrosPorCliente(key);
			if (lista == null) continue;
			if (lista.size() == 0) {
				System.out.println("\nMENSAGEM: Lista de sinistros vazia!\n");
			} else {
				for (Sinistro sinistro: lista) {
					System.out.println(sinistro.toString() + "\n");
				}
			}
		}
	}
	
	private static void listarVeiculos() {
		if (seguradoras.get(0) == null) System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
		
		for (Seguradora seguradora: seguradoras) {
			System.out.println("\n" + seguradora.getNome().toUpperCase() + ":");

			List<Veiculo> lista = seguradora.listarVeiculos();
			if (lista.size() == 0) {
				System.out.println("\nMENSAGEM: Lista de clientes vazia!\n");
			} else {
				for (Veiculo veiculo: lista) {
					System.out.println(veiculo.toString() + "\n");
				}
			}
		}
	}
	
	private static void listarVeiculos(String key) {
		if (seguradoras.get(0) == null) System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
		
		for (Seguradora seguradora: seguradoras) {
			System.out.println("\n" + seguradora.getNome().toUpperCase() + ":");

			List<Veiculo> lista = seguradora.listarVeiculos(key);
			if (lista == null) {
				printError("\nMENSAGEM: Cliente " + key + " nao encontrado.\n");
			} else if (lista.size() == 0) {
				System.out.println("\nMENSAGEM: Lista de veiculos vazia!\n");
			} else {
				for (Veiculo veiculo: lista) {
					System.out.println(veiculo.toString() + "\n");
				}
			}
		}
	}
	
	private static void listarSeguradoras() {
		if (seguradoras.get(0) == null) System.out.println("Nenhuma seguradora no sistema! Cadastre alguma para continuar.");
		for (Seguradora seguradora: seguradoras) {
			System.out.println("SEGURADORAS:\n");
			System.out.println(seguradora.toString() + "\n");
		}
	}
	
	private static boolean excluirCliente() {	
		String key = "";
		String tipo = InputReader.lerTipoCliente();
		
		if (tipo.equals("PF")) key = InputReader.lerCPF();
		else key = InputReader.lerCNPJ();
		
		String nome = "";
		int segId = -1;
		while (segId < 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		if (seguradora.removerCliente(key)) {
			System.out.println(String.format("\nMENSAGEM: Cliente de %s %s removido com sucesso!\n", tipo.equals("PF") ? "CPF" : "CNPJ" ,key));
			return true;
		}
			
		System.out.println(String.format("\nMENSAGEM: Cliente de %s %s nao encontrado na seguradora %s.\n", tipo.equals("PF") ? "CPF" : "CNPJ", key, seguradora.getNome()));
		return false;
	}

	private static boolean excluirVeiculo() {
		String key = "";
		String tipo = InputReader.lerTipoCliente();
		
		if (tipo.equals("PF")) key = InputReader.lerCPF();
		else key = InputReader.lerCNPJ();
		
		String nome = "";
		int segId = -1;
		while (segId < 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		String placa = InputReader.lerPlaca("Placa do veiculo a remover: ");
		
		String frota;
		boolean ret = false;
		if (!tipo.equals("PF")) {
			frota = InputReader.lerString("ID da frota de onde remover: ");
			ret = seguradora.removerVeiculo(placa, frota, key);
		} else ret = seguradora.removerVeiculo(placa, key);
		
		
		// Verificamos o resultado da tentativa de remocao e informamos o usuario de acordo
		if (ret) {
			System.out.println("\nMENSAGEM: Veiculo de placa " + placa + " removido com sucesso!\n");
			return true;
		}
		System.out.println("\nMENSAGEM: Nao encontramos veiculo com placa: " + placa + " no cliente " + key + " da seguradora " + seguradora.getNome() + ".\n");
		return false;
	}
	
	private static boolean excluirSinistro() {
		String nome = "";
		int segId = -1;
		while (segId < 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		int idSinistro = InputReader.lerInteiro("ID do sinistro a remover: ");
		
		
		
		// Verificamos o resultado da tentativa de remocao e informamos o usuario de acordo
		if (seguradora.removerSinistro(idSinistro)) {
			System.out.println("\nMENSAGEM: Sinistro de ID" + idSinistro + " removido com sucesso!\n");
			return true;
		}
		System.out.println("\nMENSAGEM: Nao encontramos sinistro com ID: " + idSinistro + " na seguradora " + nome + ".\n");
		return false;
	}
	
	private static boolean excluirSeguro() {
		String nome = "";
		int segId = -1;
		while (segId < 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		int idSeguro = InputReader.lerInteiro("ID do seguro a excluir: ");
		
		if (seguradora.cancelarSeguro(idSeguro)) {
			System.out.println("\nMENSAGEM: Seguro de ID " + idSeguro + " removido com sucesso!\n");
			return true;
		}
		
		System.out.println("\nMENSAGEM: Nao encontramos seguro com ID " + idSeguro + ".\n");
		return true;
	}
	
	private static void calcularReceita() {
		String nome = "";
		int segId = -1;
		while (segId < 0) {
			nome = InputReader.lerString("Nome da seguradora: ");
			segId = existeSeguradora(nome);
		}
		Seguradora seguradora = seguradoras.get(segId);
		
		System.out.println(String.format("\n%s tem R$%.2f de receita.\n", seguradora.getNome(), seguradora.calcularReceita()));
	}
	
	// "rodarMenu()"
	public static void main(String[] args) {
		// Instanciamos alguns valores para o programa logo abaixo já possuir alguns objetos quando iniciar
		
		// Instanciar pelo menos 1 objeto da classe Seguradora
		Seguradora seguradora = new Seguradora("Seguros do Jose", "46801563", "segjose@gmail.com", "Campinas - SP");
		
		// Cadastrar e remover pelo menos um Cliente (ClientePF ou ClientePJ)
		//   System.out.println(seguradora.toString());
		ClientePF cliente1 = new ClientePF("Joao", "(19) 982231314", "Rua dos Joaos - 111", "joao@gmail.com", "60310649013", LocalDate.now(), "Ensino Medio", "Masculino");
		seguradora.cadastrarCliente(cliente1);
		//   System.out.println(seguradora.toString());
		seguradora.removerCliente(cliente1.getCpf());
		//   System.out.println(seguradora.toString());
		
		// Cadastrar pelo menos 2 clientes em Seguradora (sem remover), sendo 1 do tipo ClientePF e 1 do tipo ClientePJ;
		ClientePF cliente2 = new ClientePF("Joao", "(19) 982231314", "Rua dos Joaos - 111", "joao@gmail.com", "60310649013", LocalDate.now(), "Ensino Medio", "Masculino");
		seguradora.cadastrarCliente(cliente2);
		ClientePJ cliente3 = new ClientePJ("Venda do Joao", "(19) 359439485", "Rua dos Joaos - 111", "joao.venda@gmail.com", "86658785000100", LocalDate.now());
		seguradora.cadastrarCliente(cliente3);
		//   System.out.println(seguradora.toString());
		
		// Adicionar pelo menos 1 Veiculo em cada Cliente instanciado
		Veiculo veiculo1 = new Veiculo("ABC1234", "Fiat", "Fusca", 1983);
		seguradora.adicionarVeiculo(veiculo1, cliente2.getCpf());
		//   System.out.println(cliente2.toString());
		Veiculo veiculo2 = new Veiculo("ABC1D23", "Calloi", "Bicicleta do grau", 2000);
		seguradora.adicionarVeiculo(veiculo2, cliente3.getCnpj());
		//   System.out.println(cliente3.toString());
		
		SeguroPF seguro = new SeguroPF(LocalDate.now(), LocalDate.now().plusDays(365), seguradora, veiculo2, cliente2);
		seguradora.getListaSeguros().add(seguro);
		
		seguro.getListaCondutores().add(new Condutor(cliente2.getCpf(), cliente2.getNome(), cliente2.getTelefone(), cliente2.getEndereco(), cliente2.getEmail(), cliente2.getDataNascimento()));
		
		Sinistro sinistro = new Sinistro(LocalDate.now(), "Rua pitagoras, 345", seguro.getListaCondutores().get(0), seguro);
		seguro.getListaSinistros().add(sinistro);
		
		seguradoras.add(seguradora);
		
		MenuOperacoes op;
		do {
			op = lerOperacaoMenu();
			executarOperacaoMenu(op);
		} while(op != MenuOperacoes.SAIR);
	}
}
	