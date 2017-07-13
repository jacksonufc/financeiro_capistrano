package dao;

import modelo.Despesa;
import modelo.Mes;

public interface DespesaDAO extends GenericDAO<Despesa>{
	
	public Double soma(Mes mes, int ano);
}
