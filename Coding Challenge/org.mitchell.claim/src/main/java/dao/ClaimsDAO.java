package dao;

import java.util.Date;
import java.util.List;

import model.Claim;

/**
 * ClaimsDAO interface contains claim specific functions
 * 
 * @author Shalima
 *
 */
public interface ClaimsDAO extends BaseDAO<Claim> {

	/**
	 * Find all the claims in the given date range
	 * 
	 * @param from
	 * @param to
	 */
	public List<Claim> getClaimsInLossDateRange(Date from, Date to);

	/**
	 * Finds the particular claim with given claim number
	 * 
	 * @param id
	 * @return Claim
	 */
	public Claim read(String id);

	/**
	 * Deletes the existing entity with the given claim number form database
	 * 
	 * @param id
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	public String delete(String id);

}
