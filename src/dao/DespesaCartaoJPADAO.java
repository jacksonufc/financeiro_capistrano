package dao;

import java.util.List;
import javax.persistence.Query;

import modelo.DespesaCartao;
import modelo.Mes;

public class DespesaCartaoJPADAO extends GenericJPADAO<DespesaCartao> implements DespesaCartaoDAO{
	
	public DespesaCartaoJPADAO(){
		this.persistentClass = DespesaCartao.class;
	}

	@Override
	public List<DespesaCartao> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("DespesaCartao.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public List<DespesaCartao> buscarPorMesAno(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaCartao.buscarPorMesAno");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("DespesaCartao.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}


}
