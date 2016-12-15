package ps.solspan.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ps.solspan.model.Usuario;
import ps.solspan.repository.Usuarios;

public class UsuarioDao implements Usuarios {
	
	@Inject
	private EntityManager em;
	
	public Usuario byId(int id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> all() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		criteria.select(criteria.from(Usuario.class));
		
		return em.createQuery(criteria).getResultList();
	}
}
