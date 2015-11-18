package dao.impl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.ClaimsDAO;
import dao.DAOFactory;
import junit.framework.TestCase;
import model.Claim;
import model.Lossinfotype;
import model.Vehicleinfotype;

public class ClaimsDAOImplTest extends TestCase {

	ClaimsDAO claimsDao;

	@SuppressWarnings("resource")
	@Override
	protected void setUp() throws Exception {
		//
		super.setUp();
		DAOFactory daoFactory = new ClassPathXmlApplicationContext("springTest.xml").getBean(DAOFactory.class);
		claimsDao = daoFactory.getClaimsDAO();
	}

	public void testGetClaimsInLossDateRange() {
		Claim claim = getClaim();
		claimsDao.save(claim);
		//set other values
		claim.setClaimnumber("12005");
		claim.setLossdate(new GregorianCalendar(2015, 10, 13).getTime());
		Vehicleinfotype vehicle = (Vehicleinfotype) claim.getVehicleinfotypes().get(0);
		vehicle.setVin("2345");
		claimsDao.save(claim);
		claim.setLossdate(new GregorianCalendar(2014, 10, 12).getTime());
		claim.setClaimnumber("12006");
		vehicle = (Vehicleinfotype) claim.getVehicleinfotypes().get(0);
		vehicle.setVin("2346");
		claimsDao.save(claim);
		List<Claim> claimsList = claimsDao.read();
		assertEquals(4, claimsList.size());

		claimsList = claimsDao.getClaimsInLossDateRange(new GregorianCalendar(2015, 10, 12).getTime(),
				new GregorianCalendar(2015, 11, 15).getTime());
		assertEquals(3, claimsList.size());

	}

	public void testSave() {
		Claim claim = getClaim();
		String status = claimsDao.save(claim);
		claim = claimsDao.read("12004");
		assertNotNull(claim);
		assertEquals(status, "SUCCESS");
	}

	public void testUpdate() {
		Claim claim = getClaim();
		String status = claimsDao.save(claim);
		claim.setStatus("CLOSED");
		status = claimsDao.update(claim);
		claim = claimsDao.read("12004");
		assertEquals(status, "SUCCESS");
		assertEquals(claim.getStatus(), "CLOSED");
	}

	public void testDelete() {
		List<Claim> claimsList = claimsDao.read();
		assertEquals(1, claimsList.size());
		String status = claimsDao.save(getClaim());
		claimsList = claimsDao.read();
		assertEquals(status, "SUCCESS");
		assertEquals(2, claimsList.size());
		status = claimsDao.delete(getClaim().getClaimnumber());
		claimsList = claimsDao.read();
		assertEquals(status, "SUCCESS");
		assertEquals(1, claimsList.size());
	}

	private Claim getClaim() {
		Claim claim = new Claim();
		claim.setClaimnumber("12004");
		claim.setClaimfirstname("Mitchell");
		claim.setClaimlastname("Int");
		claim.setStatus("OPEN");
		claim.setLossdate(new GregorianCalendar(2015, 10, 12).getTime());

		Vehicleinfotype vehicle = new Vehicleinfotype();
		vehicle.setClaim(claim);
		vehicle.setLicplate("BT34FT");
		vehicle.setVin("1234");
		vehicle.setLicplateexpdate(new GregorianCalendar(2015, 10, 13).getTime());

		List<Vehicleinfotype> vehicleList = new ArrayList<>();
		vehicleList.add(vehicle);
		claim.setVehicleinfotypes(vehicleList);

		Lossinfotype lossInfo = new Lossinfotype();
		lossInfo.setCauseoflosscode("Collision");
		lossInfo.setClaim(claim);
		claim.setLossinfotype(lossInfo);

		return claim;
	}

}
