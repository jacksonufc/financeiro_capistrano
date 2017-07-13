package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.Mes;
import modelo.Receita;

public class ReceitaJPADAO extends GenericJPADAO<Receita> implements ReceitaDAO{
	
	private static final long serialVersionUID = 1L;
	
	public ReceitaJPADAO(){
		this.persistentClass = Receita.class;
	}

	@Override
	public List<Receita> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("Receita.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<Receita> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("Receita.buscarPorMesAno");
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("Receita.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}

}
