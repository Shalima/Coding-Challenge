package resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import jaxb.MitchellClaimType;
import model.Claim;
import model.Vehicleinfotype;
import service.MitchellService;

/**
 * MitchellRestReource is a resource class for REST. It performs all the CRUD
 * operations.
 * 
 * @author Shalima
 *
 */
@Path("claims")
public class MitchellRestResource {

	// loads the spring xml
	MitchellService service = new ClassPathXmlApplicationContext("spring.xml").getBean(MitchellService.class);
	public static final Logger log = Logger.getLogger("MitchellRestReource");

	/**
	 * Reads all the claims from the backing store
	 * 
	 * @return List<Claim>
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<Claim> getAllClaims() {
		log.info("Entered the webservice : GET");
		return service.getAllClaims();
	}

	/**
	 * Read a claim from the backing store given an id
	 * 
	 * @param id
	 * @return Claim
	 */

	@GET
	@Path("getClaim/{claimNumber}")
	@Produces(MediaType.TEXT_PLAIN)
	public Claim read(@PathParam("claimNumber") String claimNumber) {
		log.info("Entered the webservice : GET");
		Claim claim = (claimNumber == null) ? null : service.read(claimNumber);
		log.info("Leaving the webservice : GET");
		return claim;
	}

	/**
	 * Create a new claim in the backing store
	 * 
	 * @param XMl
	 *            input
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String create(MitchellClaimType mitchellClaim) {
		log.info("Entered the webservice : POST");
		// converts jaxb class to model class and then sends the service request
		String status = (mitchellClaim == null) ? "FAIL : Wrong input" : service.create(mitchellClaim.getClaim());
		log.info("Leaving the webservice : POST");
		return status;
	}

	/**
	 * Updates already existing claim in the backing store
	 * 
	 * @param xml
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String update(MitchellClaimType mitchellClaim) {
		log.info("Entered the webservice : PUT");
		// converts jaxb class to model class and then sends the service request
		String status = (mitchellClaim == null) ? "FAIL : Wrong input" : service.update(mitchellClaim.getClaim());
		log.info("Leaving the webservice : PUT");
		return status;
	}

	/**
	 * Delete a claim from the backing store with the given id
	 * 
	 * @param id
	 * @return "SUCCESS" if it is successfully persisted "FAIL" if not
	 */
	@DELETE
	@Path("deleteClaim/{claimNumber}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("claimNumber") String claimNumber) {
		log.info("Entered the webservice : DEETE");
		String status = (claimNumber == null) ? "FAIL : Wrong input" : service.delete(claimNumber);
		log.info("Leaving the webservice : DELETE");
		return status;
	}

	/**
	 * Reads claims in the lossDate date range given
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return List<Claim>
	 */
	@GET
	@Path("getInDateRange/{fromDate}/{toDate}")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Claim> getClaimsInLossRange(@PathParam("fromDate") String from, @PathParam("toDate") String to)
			throws ParseException {
		log.info("Entered the webservice : GET");
		List<Claim> claimList = new ArrayList<>();
		if (from != null && to != null) {
			log.info("Parsed the string to dates");
			DateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date fromDate = newFormat.parse(from);
			Date toDate = newFormat.parse(to);

			claimList = service.getClaimsByDateRange(fromDate, toDate);
		}
		log.info("Leaving the webservice : GET");
		return claimList;
	}

	/**
	 * Returns the vehicle details given a claim number and lic plate
	 * 
	 * @param claimNumber
	 * @param vehicleLicPlate
	 * @return Vehicleinfotype
	 */
	@GET
	@Path("getVehicle/{claimNumber}/{vin}")
	@Produces(MediaType.TEXT_PLAIN)
	public Vehicleinfotype getVehicle(@PathParam("claimNumber") String claimNumber, @PathParam("vin") String vin)
			throws ParseException {
		log.info("Entered the webservice : GET");
		Vehicleinfotype vehicle = (vin == null && claimNumber == null) ? null : service.getVehicle(claimNumber, vin);
		log.info("Leaving the webservice : GET");
		return vehicle;
	}
}
