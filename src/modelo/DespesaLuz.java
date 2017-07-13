package modelo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DespesaLuz.buscarPorAno", 
			query="SELECT dl FROM DespesaLuz dl WHERE dl.ano = :ano"),
	@NamedQuery(name="DespesaLuz.buscarPorMesAno", 
			query="SELECT dl FROM DespesaLuz dl WHERE dl.mes = :mes AND dl.ano = :ano"),
	@NamedQuery(name="DespesaLuz.soma", 
			query="SELECT SUM(dl.valor) FROM DespesaLuz dl WHERE dl.mes = :mes AND dl.ano = :ano")
})
public class DespesaLuz  extends Despesa{

}
