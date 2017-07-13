package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.Mes;
import modelo.ReceitaFetamce;

public class ReceitaFetamceJPADAO extends GenericJPADAO<ReceitaFetamce> implements ReceitaFetamceDAO{
	
	public ReceitaFetamceJPADAO(){
		this.persistentClass = ReceitaFetamce.class;
	}

	@Override
	public List<ReceitaFetamce> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("ReceitaFetamce.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<ReceitaFetamce> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("ReceitaFetamce.buscarPorMesAno");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("ReceitaFetamce.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}

}
