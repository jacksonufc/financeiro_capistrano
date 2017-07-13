package dao;

import java.util.List;

import modelo.Mes;
import modelo.Receita;

public interface ReceitaDAO extends GenericDAO<Receita>{

	public List<Receita> buscarPorAno(int ano);
	public List<Receita> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);
}
