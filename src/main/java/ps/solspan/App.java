package ps.solspan;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import ps.solspan.resource.AuthenticationResource;
import ps.solspan.resource.UsuarioResource;
import ps.solspan.transformer.JsonTransformer;
import ps.solspan.util.Path;
import spark.debug.DebugScreen;

public class App {
	
	@Inject
	private JsonTransformer transformer;
	
	@Inject
	private AuthenticationResource authResource;
	
	@Inject
	private UsuarioResource usuarioResource;
	
	public void createApp(@Observes ContainerInitialized event) {
		
		port(8989);
		
		/** Verificação de autenticação de usuários **/
		
		before("/api/*", authResource.checkUsuario());
		
		/** Endpoints para Autenticação **/
		
		post(Path.LOGIN, authResource.login(), transformer);
		get(Path.LOGOUT, authResource.logout(), transformer);
		
		/** Endpoints para Usuário **/
		
		get(Path.USUARIO_ID, usuarioResource.byId(), transformer);
		get(Path.USUARIO_INDEX, usuarioResource.all(), transformer);
		post(Path.USUARIO_INDEX, usuarioResource.save(), transformer);
		put(Path.USUARIO_ID, usuarioResource.update(), transformer);
		delete(Path.USUARIO_ID, usuarioResource.delete(), transformer);
		
		after((request, response) -> {
			response.type("application/json");
		});
		
		DebugScreen.enableDebugScreen();
	}
}
