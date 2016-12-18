package ps.solspan.repository;

import ps.solspan.model.Usuario;

public interface Usuarios extends Repository<Usuario> {
	
	public Usuario byLogin(String login);
}
