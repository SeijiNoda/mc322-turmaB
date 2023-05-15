package mc322.menu;

public enum SubmenuOperacoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar clientes"),
	LISTAR_SINISTROS("Listar sinistros"),
	LISTAR_VEICULOS("Listar veiculo"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	private final String descricao;
	
	SubmenuOperacoes(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
