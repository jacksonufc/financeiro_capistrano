package dao;

import java.util.List;
import modelo.DespesaExtra;
import modelo.Mes;

public interface DespesaExtraDAO extends GenericDAO<DespesaExtra>{
	
	public List<DespesaExtra> buscarPorAno(int ano);
	public List<DespesaExtra> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);

}
