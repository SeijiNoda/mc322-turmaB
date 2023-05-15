package mc322.menu;

public enum MenuOperacoes {
	CADASTROS(1, "Cadastrar", new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.CADASTRAR_SEGURADORA,
			SubmenuOperacoes.VOLTAR
	}),
	LISTAR(2, "Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.LISTAR_CLIENTES,
			SubmenuOperacoes.LISTAR_VEICULOS,
			SubmenuOperacoes.LISTAR_SINISTROS,
			SubmenuOperacoes.VOLTAR
	}),
	EXCLUIR(3, "Excluir", new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_SINISTRO
	}),
	GERAR_SINISTRO(4, "Gerar Sinistro", new SubmenuOperacoes[] { SubmenuOperacoes.VOLTAR }),
	TRANSFERIR_SEGURO(5, "Transferir Seguro",  new SubmenuOperacoes[] { SubmenuOperacoes.VOLTAR }),
	CALCULAR_RECEITA(6, "Calcular Receita",  new SubmenuOperacoes[] { SubmenuOperacoes.VOLTAR }),
	SAIR(0, "Sair", new SubmenuOperacoes[] {});
	
	private final int id;
	private final String operacao;
	private final SubmenuOperacoes[] submenu;
	
	MenuOperacoes(int id, String operacao, SubmenuOperacoes[] submenu) {
		this.id = id;
		this.operacao = operacao;
		this.submenu = submenu;
	}
	
	// metodo enum.ordinal()
	
	public int getId() {
		return this.id;
	}
	
	public String getOperacao() {
		return this.operacao;
	}
	
	public SubmenuOperacoes[] getSubmenu() {
		return this.submenu;
	}
}
