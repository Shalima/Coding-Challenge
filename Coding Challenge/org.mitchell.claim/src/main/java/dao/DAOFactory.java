package dao;

/**
 * DAOFactory class represents the factory object for creating the DAO objects
 * The members are injected with the help of Spring
 * 
 * @author Shalima
 *
 */
public class DAOFactory {

	private ClaimsDAO claimsDAO;
	private VehicleDAO vehicleDAO;

	/**
	 * @return the claimsDAO
	 */
	public ClaimsDAO getClaimsDAO() {
		return claimsDAO;
	}

	/**
	 * @param claimsDAO
	 *            the claimsDAO to set
	 */
	public void setClaimsDAO(ClaimsDAO claimsDAO) {
		this.claimsDAO = claimsDAO;
	}

	/**
	 * @return the vehicleDAO
	 */
	public VehicleDAO getVehicleDAO() {
		return vehicleDAO;
	}

	/**
	 * @param vehicleDAO
	 *            the vehicleDAO to set
	 */
	public void setVehicleDAO(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

}
