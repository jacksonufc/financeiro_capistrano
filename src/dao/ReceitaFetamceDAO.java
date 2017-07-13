package dao;

import java.util.List;
import modelo.Mes;
import modelo.ReceitaFetamce;

public interface ReceitaFetamceDAO extends GenericDAO<ReceitaFetamce>{
	
	public List<ReceitaFetamce> buscarPorAno(int ano);
	public List<ReceitaFetamce> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);

}
