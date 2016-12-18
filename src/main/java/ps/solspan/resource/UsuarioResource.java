package ps.solspan.resource;

import java.util.Date;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import ps.solspan.model.Usuario;
import ps.solspan.repository.Usuarios;
import spark.Request;
import spark.Response;
import spark.Route;

public class UsuarioResource {
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Gson gson;
	
	public Route save() {
		return save;
	}
	
	private Route save = (Request req, Response res) -> {
		
		Usuario usuario = gson.fromJson(req.body(), Usuario.class);
		
		String salt = BCrypt.gensalt();
		String senha = BCrypt.hashpw(usuario.getSenha(), salt);
		
		usuario.setSenha(senha);
		usuario.setSalt(salt);
		usuario.setDataAlteracao(new Date());
		
		usuarios.save(usuario);
		
		res.status(201);
		res.header("Location", req.pathInfo());
		
		return usuario;
	};
	
	public Route byId() {
		return byId;
	}

	private Route byId = (Request req, Response res) -> {
		
		String pathId = req.params("id");
		
		int id = Integer.parseInt(pathId);
		
		return usuarios.byId(id);
	};
	
	private Route update = (Request req, Response res) -> {
		
		Usuario temp = gson.fromJson(req.body(), Usuario.class);
		
		String pathId = req.params("id");
		
		int id = Integer.parseInt(pathId);
		
		Usuario usuario = usuarios.byId(id);
		
		if (usuario != null) {
			
			usuario.setNome(temp.getNome());
			
			if (temp.getSenha() != null && !temp.getSenha().isEmpty() && !temp.getSenha().equals(usuario.getSenha())) {
				
				String salt = BCrypt.gensalt();
				String senha = BCrypt.hashpw(temp.getSenha(), salt);
				
				usuario.setSenha(senha);
			}
			
			usuario.setDataAlteracao(new Date());
			
			usuarios.update(usuario);
			res.status(200);
			res.header("Location", req.pathInfo());
			
		} else {
			res.status(304);
		}
		
		return usuario;
	};
	
	public Route update() {
		return update;
	}
	
	private Route delete = (Request req, Response res) -> {
		
		String pathId = req.params("id");
		
		int id = Integer.parseInt(pathId);
		
		Usuario usuario = usuarios.byId(id);
		
		if (usuario != null) {
			
			usuarios.delete(usuario);
			res.status(200);
			
		} else {
			res.status(304);
		}
		
		return res;
	};

	public Route delete() {
		return delete;
	}

	public Route all() {
		return all;
	}
	
	private Route all = (Request req, Response res) -> {
		return usuarios.all();
	};
}
