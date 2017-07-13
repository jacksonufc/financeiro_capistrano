package dao;

import java.util.List;

import modelo.Mes;
import modelo.ReceitaExtra;


public interface ReceitaExtraDAO extends GenericDAO<ReceitaExtra>{
	
	public List<ReceitaExtra> buscarPorAno(int ano);
	public List<ReceitaExtra> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);

}
