package mc322.menu;

public enum SubmenuOperacoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	VOLTAR("Voltar");
	
	private final String descricao;
	
	SubmenuOperacoes(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
