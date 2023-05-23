package mc322.condutor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mc322.sinistro.Sinistro;

public class Condutor {
	private String cpf;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private LocalDate dataNascimento;
	private List<Sinistro> listaSinistros;
	
	
	
	public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNascimento) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.listaSinistros = new ArrayList<Sinistro>();
	}
	
	public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNascimento, List<Sinistro> listaSinistros) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.listaSinistros = listaSinistros;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
	public void setListaSinistros(List<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}
	
	public boolean adicionarSinistro(Sinistro novo) {
		this.listaSinistros.add(novo);
		return true;
	}
	
	public String toString() {
		return String.format("CONDUTOR:\n%s - %s\n%s\n", this.nome, this.cpf, this.email);
	}
}
