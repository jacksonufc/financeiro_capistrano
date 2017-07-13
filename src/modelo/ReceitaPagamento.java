package modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="ReceitaPagamento.buscarPorAno", 
			query="SELECT rp FROM ReceitaPagamento rp WHERE rp.ano = :ano"),
	@NamedQuery(name="ReceitaPagamento.buscarPorSecretaria", 
			query="SELECT rp FROM ReceitaPagamento rp WHERE rp.secretaria.id = :idSecretaria AND rp.ano = :ano"),
	@NamedQuery(name="ReceitaPagamento.soma", 
			query="SELECT SUM(rp.valor) FROM ReceitaPagamento rp WHERE rp.mes = :mes AND rp.ano = :ano")
})
public class ReceitaPagamento extends Receita{
	
	@ManyToOne
	private Secretaria secretaria;
	//@Enumerated(EnumType.STRING)
	//private Serie serie;
	
	public ReceitaPagamento(){
		this.secretaria = new Secretaria();
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	//public Serie getSerie() {
	//	return serie;
	//}

	//public void setSerie(Serie serie) {
	//	this.serie = serie;
	//}

}
