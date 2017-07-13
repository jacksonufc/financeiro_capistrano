package modelo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DespesaCartao.buscarPorAno", 
			query="SELECT dc FROM DespesaCartao dc WHERE dc.ano = :ano"),
	@NamedQuery(name="DespesaCartao.buscarPorMesAno", 
			query="SELECT dc FROM DespesaCartao dc WHERE dc.mes = :mes AND dc.ano = :ano"),
	@NamedQuery(name="DespesaCartao.soma", 
			query="SELECT SUM(dc.valor) FROM DespesaCartao dc WHERE dc.mes = :mes AND dc.ano = :ano")
})
public class DespesaCartao extends Despesa{

}
