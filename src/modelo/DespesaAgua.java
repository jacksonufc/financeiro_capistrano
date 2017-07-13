package modelo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DespesaAgua.buscarPorAno", 
			query="SELECT da FROM DespesaAgua da WHERE da.ano = :ano"),
	@NamedQuery(name="DespesaAgua.buscarPorMesAno", 
			query="SELECT da FROM DespesaAgua da WHERE da.mes = :mes AND da.ano = :ano"),
	@NamedQuery(name="DespesaAgua.soma", 
			query="SELECT SUM(da.valor) FROM DespesaAgua da WHERE da.mes = :mes AND da.ano = :ano")
})
public class DespesaAgua extends Despesa{

}
