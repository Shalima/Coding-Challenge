package dao;

import java.util.List;

/**
 * BaseDAO is an interface that is implemented by all the DAO classes
 * 
 * @author Shalima
 *
 * @param <T>
 */
public interface BaseDAO<T> {

	/**
	 * Saves the object into the DB
	 * 
	 * @param entity
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	public String save(T entity);

	/**
	 * This method updates the already existing entity in the DB
	 * 
	 * @param entity
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	public String update(T entity);

	/**
	 * Reads all the entities from the database
	 * 
	 * @return List
	 */
	public List<T> read();
}