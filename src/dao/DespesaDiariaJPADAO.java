package dao;

import java.util.List;
import javax.persistence.Query;

import modelo.DespesaDiaria;
import modelo.Mes;

public class DespesaDiariaJPADAO extends GenericJPADAO<DespesaDiaria> implements DespesaDiariaDAO{
	
	public DespesaDiariaJPADAO(){
		this.persistentClass = DespesaDiaria.class;
	}

	@Override
	public List<DespesaDiaria> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("DespesaDiaria.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<DespesaDiaria> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaDiaria.buscarPorMesAno");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaDiaria.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}


}
