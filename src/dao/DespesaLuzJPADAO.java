package dao;

import java.util.List;

import javax.persistence.Query;
import modelo.DespesaLuz;
import modelo.Mes;

public class DespesaLuzJPADAO extends GenericJPADAO<DespesaLuz> implements DespesaLuzDAO{

	public DespesaLuzJPADAO(){
		this.persistentClass = DespesaLuz.class;
	}

	@Override
	public List<DespesaLuz> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("DespesaLuz.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<DespesaLuz> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaLuz.buscarPorMesAno");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaLuz.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}


}
