package dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.VehicleDAO;
import model.Vehicleinfotype;

/**
 * VehicleDAOImpl implements the VehicleDAO interface and implements the
 * functions related to Vehicles in Database
 * 
 * @author Shalima
 *
 */
public class VehicleDAOImpl extends BaseDAOImpl<Vehicleinfotype> implements VehicleDAO {

	// Sets the sessionFactory in base class
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * FInds the vehicle with given claim number and vin
	 * 
	 * @param claimNumber
	 * @param vin
	 * @return VehicleInfoType
	 */
	public Vehicleinfotype getVehicle(String claimNumber, String vin) {
		Session session = null;
		Vehicleinfotype vehicle = null;
		try {
			session = sessionFactory.openSession();
			vehicle = (Vehicleinfotype) session
					.createQuery(
							"from Vehicleinfotype where vin = '" + vin + "' AND claimnumber = '" + claimNumber + "'")
					.list().get(0);
			log.info("Retrieved a specific vehicle");
			session.close();
		} catch (Exception e) {
			log.error(e.getStackTrace());
			throw e;
		}

		finally {

			if (session != null && session.isOpen())
				session.close();
		}
		return vehicle;
	}

}
