package modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Funcionario.buscarPorTipo", 
			query="SELECT f FROM Funcionario f WHERE f.tipo = :tipo AND f.ativo = true"),
	@NamedQuery(name="Funcionario.buscarAtivo", 
			query="SELECT f FROM Funcionario f WHERE f.ativo = true"),
	@NamedQuery(name="Funcionario.buscarInativo", 
			query="SELECT f FROM Funcionario f WHERE f.ativo = false")	
})
public class Funcionario {
	@Id
	private String cpf;
	private String nome;
	@Enumerated(EnumType.STRING)
	private TipoFuncionario tipo;
	private int salario;
	private boolean ativo;
	
	public Funcionario(){
		this.ativo = true;
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
		this.nome = nome.toUpperCase();
	}
	
	public TipoFuncionario getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoFuncionario tipo) {
		this.tipo = tipo;
	}
	
	public int getSalario() {
		return salario;
	}
	
	public void setSalario(int salario) {
		this.salario = salario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
