package ps.solspan.controller;

import java.util.List;

import javax.inject.Inject;

import ps.solspan.model.Usuario;
import ps.solspan.repository.Usuarios;

public class UsuarioController {
	
	@Inject
	private Usuarios usuarios;
	
	public Usuario byId(int id) {
		return usuarios.byId(id);
	}
	
	public List<Usuario> all() {
		return usuarios.all();
	}
	
	public void save(Usuario u) {
		usuarios.save(u);
	}
}
