package dao;

import java.util.List;
import javax.persistence.Query;
import modelo.Mes;
import modelo.ReceitaPagamento;

public class ReceitaPagamentoJPADAO extends GenericJPADAO<ReceitaPagamento> implements ReceitaPagamentoDAO{
	
	private static final long serialVersionUID = 1L;
	
	public ReceitaPagamentoJPADAO(){
		this.persistentClass = ReceitaPagamento.class;
	}

	@Override
	public List<ReceitaPagamento> buscarPorAno(int ano) {
		Query query = getEm().createNamedQuery("ReceitaPagamento.buscarPorAno");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	@Override
	public ReceitaPagamento buscarPorSecretaria(long idSecretaria, int ano) {
		Query query = getEm().createNamedQuery("ReceitaPagamento.buscarPorSecretaria");
		query.setParameter("idSecretaria", idSecretaria);
		query.setParameter("ano", ano);
		return (ReceitaPagamento) query.getSingleResult();
	}

	@Override
	public Double soma(Mes mes, int ano) {
		Query query = getEm().createNamedQuery ("ReceitaPagamento.soma");
		query.setParameter ("mes", mes) ;
		query.setParameter("ano", ano);
		Double soma = (Double) query.getSingleResult();
		return soma;
	}

}
