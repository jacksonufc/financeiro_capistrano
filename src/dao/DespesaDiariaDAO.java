package dao;

import java.util.List;

import modelo.DespesaDiaria;
import modelo.Mes;

public interface DespesaDiariaDAO extends GenericDAO<DespesaDiaria>{
	
	public List<DespesaDiaria> buscarPorAno(int ano);
	public List<DespesaDiaria> buscarPorMesAno(Mes mes, int ano);
	public Double soma(Mes mes, int ano);
	
}