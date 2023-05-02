package mc322.menu;

public enum MenuOperacoes {
	SAIR(0),
	CADASTROS(1, new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO
	}),
	LISTAR(2, new SubmenuOperacoes[] {}),
	EXCLUIR(3),
	GERAR_SINISTRO(4),
	TRANSFERIR_SEGURO(5),
	CALCULAR_RECEITA(6);
	
	private final int operacao;
	
	MenuOperacoes(int operacao, SubmenuOperacoes[] submenu) {
		this.operacao = operacao;
	}
	
	// metodo enum.ordinal()
	
	public int getOpecao() {
		return this.operacao;
	}
}
