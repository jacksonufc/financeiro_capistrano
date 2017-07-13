package dao;

import java.util.List;
import modelo.DespesaAgua;
import modelo.Mes;

public interface DespesaAguaDAO extends GenericDAO<DespesaAgua>{
	
	public List<DespesaAgua> buscarPorAno(int ano);
	public List<DespesaAgua> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);

}
