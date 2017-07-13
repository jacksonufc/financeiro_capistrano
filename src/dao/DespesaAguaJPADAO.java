package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.DespesaAgua;
import modelo.Mes;

public class DespesaAguaJPADAO extends GenericJPADAO<DespesaAgua> implements DespesaAguaDAO{
	
	public DespesaAguaJPADAO(){
		this.persistentClass = DespesaAgua.class;
	}

	@Override
	public List<DespesaAgua> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("DespesaAgua.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<DespesaAgua> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaAgua.buscarPorMesAno");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaAgua.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}

}
