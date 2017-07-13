package modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="DespesaFuncionario.buscarPorMesAno", 
			query="SELECT df FROM DespesaFuncionario df WHERE df.mes = :mes AND df.ano = :ano"),
	@NamedQuery(name="DespesaFuncionario.buscarPorAno", 
		query="SELECT df FROM DespesaFuncionario df WHERE df.ano = :ano"),
	@NamedQuery(name="DespesaFuncionario.soma", 
			query="SELECT SUM(df.valor) FROM DespesaFuncionario df WHERE df.mes = :mes AND df.ano = :ano")
})
public class DespesaFuncionario extends Despesa{
	
	@ManyToOne
	private Funcionario funcionario;
	
	public DespesaFuncionario(){
		this.funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	

}
