package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.ClaimsDAO;
import model.Claim;

/**
 * ClaimsDAOImpl class implements the ClaimsDAO interface and implements all the
 * methods in the interface that are claim specific.
 * 
 * @author Shalima
 *
 */
public class ClaimsDAOImpl extends BaseDAOImpl<Claim> implements ClaimsDAO {

	// Sets the sessionFactory in the base class
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * Find all the claims in the given date range
	 * 
	 * @param from
	 * @param to
	 */
	@SuppressWarnings("unchecked")
	public List<Claim> getClaimsInLossDateRange(Date from, Date to) {
		Session session = null;
		List<Claim> claimsList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			// create query and sets the param
			Query query = session.createQuery("from Claim where lossDate between :startDate and :endDate");
			query.setDate("startDate", from);
			query.setDate("endDate", to);
			claimsList = query.list();
			log.info("Retireved claims in the loss date range.");
			session.close();
		} catch (Exception e) {
			log.error(e.getStackTrace());
			throw e;

		} finally {

			if (session != null && session.isOpen())
				session.close();

		}
		return claimsList;
	}

	/**
	 * Finds the particular claim with given claim number
	 * 
	 * @param id
	 * @return Claim
	 */
	public Claim read(String id) {
		Session session = null;
		Claim claim = null;
		try {
			session = sessionFactory.openSession();
			claim = (Claim) session.createQuery("from Claim where Claimnumber = '" + id + "'").list().get(0);
			log.info("Retrieved a specific claim");
			session.close();
		} catch (HibernateException e) {

		} finally {

			if (session != null && session.isOpen())
				session.close();

		}
		return claim;
	}

	/**
	 * Deletes the existing entity with the given claim number form database
	 * 
	 * @param id
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	public String delete(String id) {
		Session session = null;
		String status = "SUCCESS";
		try {
			session = beginTransaction();
			Query query = session.createQuery("delete from Claim CASCADE where Claimnumber = '" + id + "'");
			query.executeUpdate();
			log.info("Deleted the claim");
			endTransaction(session);
		} catch (Exception e) {
			status = "FAIL : " + e.getStackTrace();
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

}
