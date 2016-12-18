package ps.solspan.dao;

import ps.solspan.model.Usuario;
import ps.solspan.repository.Usuarios;

public class UsuarioDao extends RepositoryDao<Usuario> implements Usuarios {
	
	public Usuario byLogin(String login) {
		return by("login", login);
	}
}
