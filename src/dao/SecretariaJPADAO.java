package dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Secretaria;

public class SecretariaJPADAO extends GenericJPADAO<Secretaria> implements SecretariaDAO{
	
	public SecretariaJPADAO(){
		this.persistentClass = Secretaria.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretaria> buscarPorAtivo() {
		Query query = getEm().createNamedQuery("Secretaria.buscarPorAtivo");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretaria> buscarPorInativo() {
		Query query = getEm().createNamedQuery("Secretaria.buscarPorInativo");
		return query.getResultList();
	}

	@Override
	public Secretaria buscarPorNome(String nome) {
		Query query = getEm().createNamedQuery("Secretaria.buscarPorNome");
		query.setParameter("nome", nome);
		return (Secretaria) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> buscarPorNomeInicial(String nome) {
		Query query = getEm().createNamedQuery("Secretaria.buscarPorNomeInicial");
		query.setParameter("nome","%"+nome+"%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretaria> buscarPorNomeParcial(String nome) {
		Query query = getEm().createNamedQuery("Secretaria.buscarPorNomeParcial");
		query.setParameter("nome","%"+nome+"%");
		return query.getResultList();
	}

}
