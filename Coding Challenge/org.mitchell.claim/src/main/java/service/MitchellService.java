package service;

import java.util.Date;
import java.util.List;

import model.Claim;
import model.Vehicleinfotype;

/**
 * MitchellService is an interface that has all the function declarations
 * performed by the webservice
 * 
 * @author Shalima
 *
 */
public interface MitchellService {
	/**
	 * Read a claim from the backing store given an id
	 * 
	 * @param id
	 * @return Claim
	 */
	public Claim read(String id);

	/**
	 * Reads all the claims from the backing store
	 * 
	 * @return List<Claim>
	 */
	public List<Claim> getAllClaims();

	/**
	 * Reads claims in the lossDate date range given
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return List<Claim>
	 */
	public List<Claim> getClaimsByDateRange(Date fromDate, Date toDate);

	/**
	 * Returns the vehicle details given a claim number and lic plate
	 * 
	 * @param claimNumber
	 * @param vehicleLicPlate
	 * @return Vehicleinfotype
	 */
	public Vehicleinfotype getVehicle(String claimNumber, String vin);

	/**
	 * Delete a claim from the backing store with the given id
	 * 
	 * @param id
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	public String delete(String id);

	/**
	 * Updates already existing claim in the backing store
	 * 
	 * @param claim
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	String update(Claim claim);

	/**
	 * Create a new claim in the backing store
	 * 
	 * @param claim
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	String create(Claim claim);

}
