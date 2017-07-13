package modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Secretaria.buscarPorAtivo", 
			query="SELECT s FROM Secretaria s WHERE s.ativo = true"),
	@NamedQuery(name="Secretaria.buscarPorInativo", 
			query="SELECT s FROM Secretaria s WHERE s.ativo = false"),
	@NamedQuery(name="Secretaria.buscarPorNome", 
			query="SELECT s FROM Secretaria s WHERE s.nome = :nome"),
	@NamedQuery(name="Secretaria.buscarPorNomeInicial", 
			query="SELECT s.nome FROM Secretaria s WHERE s.nome LIKE :nome AND s.ativo = true"),
	@NamedQuery(name="Aluno.buscarPorNomeParcial", 
			query="SELECT s FROM Secretaria s WHERE s.nome LIKE :nome")
	
})
public class Secretaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(unique=true, nullable = false)
	private String nome;
	private boolean ativo;
	
	public Secretaria(){
		this.ativo = true;
	}
	
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}
	
    public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	
}
