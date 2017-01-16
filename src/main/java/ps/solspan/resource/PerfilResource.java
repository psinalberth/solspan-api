package ps.solspan.resource;

import java.util.Date;

import javax.inject.Inject;

import com.google.gson.Gson;

import ps.solspan.model.Perfil;
import ps.solspan.repository.Perfis;
import spark.Request;
import spark.Response;
import spark.Route;

public class PerfilResource {
	
	@Inject
	private Perfis perfis;
	
	@Inject
	private Gson gson;
	
	public Route save() {
		
		Route save = (Request req, Response res) -> {
			
			Perfil perfil = gson.fromJson(req.body(), Perfil.class);
			
			perfis.save(perfil);
			
			res.status(201);
			res.header("Location", req.pathInfo());
			
			return perfil;
		};
		
		return save;
	}
	
	public Route byId() {
		
		Route byId = (Request req, Response res) -> {
			
			String pathId = req.params("id");
			
			int id = Integer.parseInt(pathId);
			
			Perfil perfil = perfis.byId(id);
			
			if (perfil != null) {
				res.status(200);
			}
			
			res.status(404);
			
			return perfil;
		};
		
		return byId;
	}
	
	public Route update() {
		
		Route update = (Request req, Response res) -> {
			
			Perfil temp = gson.fromJson(req.body(), Perfil.class);
			
			String pathId = req.params("id");
			
			int id = Integer.parseInt(pathId);
			
			Perfil perfil = perfis.byId(id);
			
			if (perfil != null) {
				
				perfil.setNome(temp.getNome());
				perfil.setDescricao(temp.getDescricao());
				perfil.setDataAlteracao(new Date());
				
				perfis.update(perfil);
				res.status(200);
				res.header("Location", req.pathInfo());
				
			} else {
				res.status(404);
			}
			
			return perfil;
		};
		
		return update;
	}

	public Route delete() {
		
		Route delete = (Request req, Response res) -> {
			
			String pathId = req.params("id");
			
			int id = Integer.parseInt(pathId);
			
			Perfil perfil = perfis.byId(id);
			
			if (perfil != null) {
				
				perfis.delete(perfil);
				res.status(200);
				
			} else {
				res.status(404);
			}
			
			return res;
		};
		
		return delete;
	}

	public Route all() {
		
		Route all = (Request req, Response res) -> {
			return perfis.all();
		};
		
		return all;
	}
}
