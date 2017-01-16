package ps.solspan;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.options;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import ps.solspan.resource.AuthResource;
import ps.solspan.resource.PerfilResource;
import ps.solspan.resource.UsuarioResource;
import ps.solspan.transformer.JsonTransformer;
import ps.solspan.util.Path;
import spark.debug.DebugScreen;

public class App {
	
	@Inject
	private JsonTransformer transformer;
	
	@Inject
	private AuthResource authResource;
	
	@Inject
	private UsuarioResource usuarioResource;
	
	@Inject
	private PerfilResource perfilResource;
	
	public void createApp(@Observes ContainerInitialized event) {
		
		port(8989);
		
		options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });
		
		 before((request, response) -> {
		        response.header("Access-Control-Allow-Origin", "*");
		        response.header("Access-Control-Request-Method", "POST, GET, DELETE, PUT");
		        response.header("Access-Control-Allow-Headers", "*");
		        response.type("application/json");
		    });
		
		/** Verificação de autenticação de usuários **/
		//before("Path.ROOT_API + *", authResource.auth());
		
		/** Endpoints para Autenticação **/
		
		post(Path.Auth.LOGIN, authResource.login(), transformer);
		get(Path.Auth.LOGOUT, authResource.logout(), transformer);
		
		/** Endpoints para Usuário **/
		
		get(Path.Usuario.ID, usuarioResource.byId(), transformer);
		get(Path.Usuario.INDEX, usuarioResource.all(), transformer);
		post(Path.Usuario.INDEX, usuarioResource.save(), transformer);
		put(Path.Usuario.ID, usuarioResource.update(), transformer);
		delete(Path.Usuario.ID, usuarioResource.delete(), transformer);
		
		/** Endpoints para Perfis **/
		
		get(Path.Perfil.ID, perfilResource.byId(), transformer);
		get(Path.Perfil.INDEX, perfilResource.all(), transformer);
		post(Path.Perfil.INDEX, perfilResource.save(), transformer);
		put(Path.Perfil.ID, perfilResource.update(), transformer);
		delete(Path.Perfil.ID, perfilResource.delete(), transformer);
		
		after((request, response) -> {
			response.type("application/json");
		});
		
		DebugScreen.enableDebugScreen();
	}
}
