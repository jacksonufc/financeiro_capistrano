package dao;

import java.util.List;
import modelo.Secretaria;

public interface SecretariaDAO extends GenericDAO<Secretaria>{
	
	public List<Secretaria> buscarPorAtivo();
	public List<Secretaria> buscarPorInativo();
	public Secretaria buscarPorNome(String nome);
	public List<String> buscarPorNomeInicial(String nome);
	public List<Secretaria> buscarPorNomeParcial(String nome);

}
