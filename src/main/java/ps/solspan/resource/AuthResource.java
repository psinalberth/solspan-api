package ps.solspan.resource;

import static spark.Spark.halt;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import ps.solspan.model.Usuario;
import ps.solspan.repository.Usuarios;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class AuthResource {
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Gson gson;
	
	public Filter checkUsuario() {
		
		Filter checkLogin = (Request req, Response res) -> {
			
			boolean auth = false;
			
			Session session = req.session(true);
			
			if (session != null && session.attribute("usuario") != null) {
				auth = true;
			}
			
			if (!auth) {
				halt(401, "You must be logged in!!!");
			}
			
			res.status(200);
		};
		
		return checkLogin;
	}
	
	public Route login() {
		
		Route route = (Request req, Response res) -> {
			
			Usuario temp = gson.fromJson(req.body(), Usuario.class);
			
			String login = temp.getLogin();
			String senha = temp.getSenha();
			
			Usuario usuario = usuarios.byLogin(login);
			
			if (usuario != null) {
				
				senha = BCrypt.hashpw(senha, usuario.getSalt());
				
				if (senha.equals(usuario.getSenha())) {
					
					Session session = req.session(true);
					session.maxInactiveInterval(100);
					
					session.attribute("usuario", usuario.getLogin());
					
					res.status(200);
					return "Welcome, " + usuario.getNome();
				}
			} else {
				return "Não encontrado!";
			}
			
			res.status(401);
			return "Not logged in";
		};
		
		return route;
	}
	
	public Route logout() {
		
		Route logout = (Request req, Response res) -> {
			
			req.session().removeAttribute("usuario");
			req.session().invalidate();
			res.status(200);
			
			return "Logout succeed!!";
		};
		
		return logout;
	}
}
