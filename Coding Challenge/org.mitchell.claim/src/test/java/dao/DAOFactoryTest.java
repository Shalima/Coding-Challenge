package dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class DAOFactoryTest extends TestCase {

	DAOFactory daoFactory = new ClassPathXmlApplicationContext("springTest.xml").getBean(DAOFactory.class);

	public void testGetClaimsDAO() {
		ClaimsDAO claimsDao = daoFactory.getClaimsDAO();
		assertNotNull(claimsDao);
	}

	public void testGetVehicleDAO() {
		VehicleDAO vehicleDao = daoFactory.getVehicleDAO();
		assertNotNull(vehicleDao);
	}

}
