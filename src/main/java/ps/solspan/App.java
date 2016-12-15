package ps.solspan;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import ps.solspan.controller.UsuarioController;
import ps.solspan.transformer.JsonTransformer;

public class App {
	
	@Inject
	private JsonTransformer transformer;
	
	@Inject
	private UsuarioController usuarioController;
	
	public void createApp(@Observes ContainerInitialized event) {
		
		port(8989);
		
		get("/api/usuarios/:id", (request, response) -> {
			
			String pathId = request.params("id");
			
			int id = Integer.parseInt(pathId);
			
			return usuarioController.byId(id);
			
		}, transformer);
		
		get("/api/usuarios", (request, response) -> {
			return usuarioController.all();
		}, transformer);
		
		after((request, response) -> {
			response.type("application/json");
		});
	}
}
