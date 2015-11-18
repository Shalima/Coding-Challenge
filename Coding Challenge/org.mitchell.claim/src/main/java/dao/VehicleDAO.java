package dao;

import model.Vehicleinfotype;

/**
 * VehicleDAO interface contains the functions specific to vehicles.
 * 
 * @author Shalima
 *
 */
public interface VehicleDAO extends BaseDAO<Vehicleinfotype> {
	/**
	 * FInds the vehicle with given claim number and vin
	 * 
	 * @param claimNumber
	 * @param vin
	 * @return VehicleInfoType
	 */
	public Vehicleinfotype getVehicle(String claimNumber, String vin);
}
