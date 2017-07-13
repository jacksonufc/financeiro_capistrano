package modelo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DespesaExtra.buscarPorAno", 
			query="SELECT de FROM DespesaExtra de WHERE de.ano = :ano"),
	@NamedQuery(name="DespesaExtra.buscarPorMesAno", 
			query="SELECT de FROM DespesaExtra de WHERE de.mes = :mes AND de.ano = :ano"),
	@NamedQuery(name="DespesaExtra.soma", 
			query="SELECT SUM(de.valor) FROM DespesaExtra de WHERE de.mes = :mes AND de.ano = :ano")
})
public class DespesaExtra  extends Despesa{
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
