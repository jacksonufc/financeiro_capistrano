package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.Mes;
import modelo.ReceitaExtra;

public class ReceitaExtraJPADAO extends GenericJPADAO<ReceitaExtra> implements ReceitaExtraDAO{
	
	private static final long serialVersionUID = 1L;
	
	public ReceitaExtraJPADAO(){
		this.persistentClass = ReceitaExtra.class;
	}

	@Override
	public List<ReceitaExtra> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("ReceitaExtra.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<ReceitaExtra> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("ReceitaExtra.buscarPorMesAno");
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery ("ReceitaExtra.soma");
		query.setParameter ("mes", mes) ;
		query.setParameter("ano", ano);
		Double soma = (Double) query.getSingleResult();
		return soma;
	}

}
