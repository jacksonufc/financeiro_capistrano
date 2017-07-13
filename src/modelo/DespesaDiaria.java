package modelo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DespesaDiaria.buscarPorAno", 
			query="SELECT dd FROM DespesaDiaria dd WHERE dd.ano = :ano"),
	@NamedQuery(name="DespesaDiaria.buscarPorMesAno", 
			query="SELECT dd FROM DespesaDiaria dd WHERE dd.mes = :mes AND dd.ano = :ano"),
	@NamedQuery(name="DespesaDiaria.soma", 
			query="SELECT SUM(dd.valor) FROM DespesaDiaria dd WHERE dd.mes = :mes AND dd.ano = :ano")
})
public class DespesaDiaria extends Despesa{

}