package dao;

import java.util.List;
import javax.persistence.Query;
import modelo.DespesaExtra;
import modelo.Mes;

public class DespesaExtraJPADAO extends GenericJPADAO<DespesaExtra> implements DespesaExtraDAO{
	
	public DespesaExtraJPADAO(){
		this.persistentClass = DespesaExtra.class;
	}

	@Override
	public List<DespesaExtra> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("DespesaExtra.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<DespesaExtra> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaExtra.buscarPorMesAno");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaExtra.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}


}
