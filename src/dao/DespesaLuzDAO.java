package dao;

import java.util.List;
import modelo.DespesaLuz;
import modelo.Mes;

public interface DespesaLuzDAO extends GenericDAO<DespesaLuz>{
	
	public List<DespesaLuz> buscarPorAno(int ano);
	public List<DespesaLuz> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);

}
