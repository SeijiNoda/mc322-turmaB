package mc322.menu;

public enum MenuOperacoes {
	CADASTROS(1, "Cadastrar", new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.CADASTRAR_SEGURADORA,
			SubmenuOperacoes.CADASTRAR_SEGURO,
			SubmenuOperacoes.VOLTAR
	}),
	LISTAR(2, "Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.LISTAR_CLIENTES,
			SubmenuOperacoes.LISTAR_SINISTROS_SEGURADORA,
			SubmenuOperacoes.LISTAR_SINISTROS_CLIENTE,
			SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE,
			SubmenuOperacoes.LISTAR_VEICULOS_SEGURADORA,
			SubmenuOperacoes.LISTAR_SEGURADORAS,
			SubmenuOperacoes.VOLTAR
	}),
	EXCLUIR(3, "Excluir", new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_SINISTRO,
			SubmenuOperacoes.EXCLUIR_SEGURO,
			SubmenuOperacoes.VOLTAR
	}),
	GERAR_SINISTRO(4, "Gerar Sinistro", new SubmenuOperacoes[] { SubmenuOperacoes.VOLTAR }),
	CALCULAR_RECEITA(5, "Calcular Receita",  new SubmenuOperacoes[] { SubmenuOperacoes.VOLTAR }),
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
