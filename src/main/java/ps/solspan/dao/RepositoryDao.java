package ps.solspan.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ps.solspan.model.Entidade;

public abstract class RepositoryDao<T extends Entidade> {
	
	@Inject
	protected EntityManager manager;
	
	public T byId(int id) {
		return this.manager.find(retornaTipo(), id);
	}
	
	public List<T> all() {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(retornaTipo());
		criteria.select(criteria.from(retornaTipo()));
		
		return manager.createQuery(criteria).getResultList();
	}
	
	public void save(T model) {
		
		manager.getTransaction().begin();
		
		try {
			
			manager.persist(model);
			manager.getTransaction().commit();
			
		} catch (Exception ex) {
			manager.getTransaction().rollback();
		} 
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> retornaTipo() {
	    
		Class<?> clazz = this.getClass();
	     
	    while (!clazz.getSuperclass().equals(RepositoryDao.class)) {
	        clazz = clazz.getSuperclass();
	    }
	     
	    ParameterizedType tipoGenerico = (ParameterizedType) clazz.getGenericSuperclass();
	    return (Class<T>) tipoGenerico.getActualTypeArguments()[0];
    }
}
