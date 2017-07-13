package dao;

import java.util.List;
import javax.persistence.Query;
import modelo.DespesaFuncionario;
import modelo.Mes;

public class DespesaFuncionarioJPADAO extends GenericJPADAO<DespesaFuncionario> implements DespesaFuncionarioDAO{
	
	public DespesaFuncionarioJPADAO(){
		this.persistentClass = DespesaFuncionario.class;
	}

	@Override
	public List<DespesaFuncionario> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaFuncionario.buscarPorMesAno");
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaFuncionario.soma");
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		return (Double) query.getSingleResult();
	}

	@Override
	public List<DespesaFuncionario> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("DespesaFuncionario.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}
}
