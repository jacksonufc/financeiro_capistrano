package dao;

import java.util.List;

import modelo.DespesaCartao;
import modelo.Mes;

public interface DespesaCartaoDAO extends GenericDAO<DespesaCartao>{
	
	public List<DespesaCartao> buscarPorAno(int ano);
	public List<DespesaCartao> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);
	
}
