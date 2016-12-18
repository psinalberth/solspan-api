package ps.solspan.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	public void update(T model) {
		
		manager.getTransaction().begin();
		
		try {
			
			manager.merge(model);
			manager.getTransaction().commit();
			
		} catch (Exception ex) {
			manager.getTransaction().rollback();
		} 
	}
	
	public void delete(T model) {
		
		manager.getTransaction().begin();
		
		try {
			
			manager.remove(model);
			manager.getTransaction().commit();
			
		} catch (Exception ex) {
			manager.getTransaction().rollback();
		}
	}
	
	public T by(String coluna, Object valor) {
		
		Class<T> clazz = retornaTipo();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		criteria.where(builder.equal(root.get(coluna), valor));
		
		try {
			
			return manager.createQuery(criteria).getSingleResult();
			
		} catch (NoResultException ex) {
			return null;
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
