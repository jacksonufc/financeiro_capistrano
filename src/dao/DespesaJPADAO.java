package dao;

import javax.persistence.Query;

import modelo.Despesa;
import modelo.Mes;

public class DespesaJPADAO extends GenericJPADAO<Despesa> implements DespesaDAO{
	
	public DespesaJPADAO(){
		this.persistentClass = Despesa.class;
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery("Despesa.soma");
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}

}
