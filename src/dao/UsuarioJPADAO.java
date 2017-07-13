package dao;

import modelo.Usuario;

public class UsuarioJPADAO extends GenericJPADAO<Usuario> implements UsuarioDAO {

	public UsuarioJPADAO(){
		this.persistentClass = Usuario.class;
	}
}