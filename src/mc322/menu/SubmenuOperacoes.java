package mc322.menu;

public enum SubmenuOperacoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	CADASTRAR_SEGURO("Cadastrar seguro"),
	
	LISTAR_CLIENTES("Listar clientes"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros por seguradora"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculos por seguradora"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
	LISTAR_SEGURADORAS("Listar seguradoras"),
	
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	EXCLUIR_SEGURO("Excluir seguro"),
	VOLTAR("Voltar");
	
	private final String descricao;
	
	SubmenuOperacoes(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
