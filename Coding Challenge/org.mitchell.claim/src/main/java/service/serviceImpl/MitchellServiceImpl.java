package service.serviceImpl;

import java.util.Date;
import java.util.List;

import dao.DAOFactory;
import model.Claim;
import model.Vehicleinfotype;
import service.MitchellService;

/**
 * MitchellServiceImpl class implements the MitchellService interface which
 * performs all the functions by the webservice
 * 
 * @author Shalima
 *
 */
public class MitchellServiceImpl implements MitchellService {

	// injected via spring
	DAOFactory daoFactory;

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Create a new claim in the backing store
	 * 
	 * @param claim
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@Override
	public String create(Claim claim) {
		return daoFactory.getClaimsDAO().save(claim);

	}

	/**
	 * Updates already existing claim in the backing store
	 * 
	 * @param claim
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@Override
	public String update(Claim claim) {
		return daoFactory.getClaimsDAO().update(claim);

	}

	/**
	 * Read a claim from the backing store given an id
	 * 
	 * @param id
	 * @return Claim
	 */
	@Override
	public Claim read(String id) {
		return daoFactory.getClaimsDAO().read(id);
	}

	/**
	 * Reads claims in the lossDate date range given
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return List<Claim>
	 */
	@Override
	public List<Claim> getClaimsByDateRange(Date fromDate, Date toDate) {
		return daoFactory.getClaimsDAO().getClaimsInLossDateRange(fromDate, toDate);
	}

	/**
	 * Returns the vehicle details given a claim number and lic plate
	 * 
	 * @param claimNumber
	 * @param vehicleLicPlate
	 * @return Vehicleinfotype
	 */
	@Override
	public Vehicleinfotype getVehicle(String claimNumber, String vin) {
		return daoFactory.getVehicleDAO().getVehicle(claimNumber, vin);
	}

	/**
	 * Delete a claim from the backing store with the given id
	 * 
	 * @param id
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@Override
	public String delete(String id) {
		return daoFactory.getClaimsDAO().delete(id);
	}

	/**
	 * Reads all the claims from the backing store
	 * 
	 * @return List<Claim>
	 */
	@Override
	public List<Claim> getAllClaims() {
		return daoFactory.getClaimsDAO().read();
	}

}
