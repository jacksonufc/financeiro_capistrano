package modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="ReceitaFetamce.buscarPorAno", 
			query="SELECT rfe FROM ReceitaFetamce rfe WHERE rfe.ano = :ano"),
	@NamedQuery(name="ReceitaFetamce.buscarPorMesAno", 
			query="SELECT rfe FROM ReceitaFetamce rfe WHERE rfe.mes = :mes AND rfe.ano = :ano"),
	@NamedQuery(name="ReceitaFetamce.soma", 
			query="SELECT SUM(rfe.valor) FROM ReceitaFetamce rfe WHERE rfe.mes = :mes AND rfe.ano = :ano")
})
public class ReceitaFetamce extends Receita{
	
	@ManyToOne
	private Secretaria secretaria;
	
	public ReceitaFetamce(){
		this.secretaria = new Secretaria();
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

}
