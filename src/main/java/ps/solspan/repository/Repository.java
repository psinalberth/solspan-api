package ps.solspan.repository;

import java.util.List;

import ps.solspan.model.Entidade;

public interface Repository <T extends Entidade> {
	
	public T byId(int id);
	
	public List<T> all();
	
	public void save(T model);
}
