package modelo;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="ReceitaExtra.buscarPorAno", 
			query="SELECT re FROM ReceitaExtra re WHERE re.ano = :ano"),
	@NamedQuery(name="ReceitaExtra.buscarPorMesAno", 
			query="SELECT re FROM ReceitaExtra re WHERE re.mes = :mes AND re.ano = :ano"),
	@NamedQuery(name="ReceitaExtra.soma", 
			query="SELECT SUM(re.valor) FROM ReceitaExtra re WHERE re.mes = :mes AND re.ano = :ano")
})
public class ReceitaExtra extends Receita{
	
}
