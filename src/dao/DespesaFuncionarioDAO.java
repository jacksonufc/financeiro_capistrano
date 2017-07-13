package dao;

import java.util.List;

import modelo.DespesaFuncionario;
import modelo.Mes;

public interface DespesaFuncionarioDAO extends GenericDAO<DespesaFuncionario>{
	
	public List<DespesaFuncionario> buscarPorMesAno(Mes mes, int ano);
	public List<DespesaFuncionario> buscarPorAno(int ano);
	public Double soma(Mes mes, int ano);

}
