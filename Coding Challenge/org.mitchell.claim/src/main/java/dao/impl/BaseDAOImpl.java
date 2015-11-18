package dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.BaseDAO;

/**
 * BaseDAOImple class implements the BaseDAO interface and contains the generic
 * database operations.
 * 
 * @author Shalima
 *
 * @param <T>
 */
public class BaseDAOImpl<T> implements BaseDAO<T> {
	// Used to log
	protected static final Logger log = Logger.getLogger(BaseDAOImpl.class);

	// injected in via Spring
	SessionFactory sessionFactory;

	/**
	 * Sets the sessionFactory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Saves the object into the backing store
	 * 
	 * @param entity
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@Override
	public String save(T object) {
		Session session = null;
		String status = "SUCCESS";
		try {
			// begin tansaction, save and close the session
			session = beginTransaction();
			session.save(object);
			log.info("Saved the object");
			endTransaction(session);
		} catch (Exception e) {
			status = "FAIL" + e.getStackTrace();
			log.error("ERROR : " + status);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
		}
		return status;
	}

	/**
	 * This method updates the already existing entity in the DB
	 * 
	 * @param entity
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@Override
	public String update(T object) {
		Session session = null;
		String status = "SUCCESS";
		try {
			session = beginTransaction();
			session.saveOrUpdate(object);
			log.info("Updated the object");
			endTransaction(session);
		} catch (Exception e) {
			status = "FAIL" + e.getStackTrace();
			log.error("ERROR : " + status);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
		}
		return status;
	}

	/**
	 * Reads all the entities from the database
	 * 
	 * @return List
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> read() {
		List<T> entityList = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			// get the entity class type and reads from the corresponding table
			entityList = session.createCriteria(
					(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
					.list();
			log.info("Retrieved list of objects in DB");
			session.close();
		} catch (Exception e) {
			log.error("ERROR : " + e.getStackTrace());
			throw e;
		}
		return entityList;
	}

	/**
	 * This method begins the transaction and creates the session
	 * 
	 * @param session
	 */
	protected void endTransaction(Session session) {
		log.info("End Transaction");
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method ends the transaction
	 * 
	 * @return
	 * @throws HibernateException
	 */
	protected Session beginTransaction() throws HibernateException {
		log.info("Begin Transaction");
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		return session;
	}
}
