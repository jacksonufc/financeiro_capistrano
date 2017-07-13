package dao;

import java.util.List;

import modelo.Mes;
import modelo.ReceitaPagamento;


public interface ReceitaPagamentoDAO extends GenericDAO<ReceitaPagamento>{
	
	public List<ReceitaPagamento> buscarPorAno(int ano);
	public ReceitaPagamento buscarPorSecretaria(long idSecretaria, int ano);
	public Double soma(Mes mes, int ano);

}
