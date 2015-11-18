package dao.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.DAOFactory;
import dao.VehicleDAO;
import junit.framework.TestCase;
import model.Vehicleinfotype;

public class VehicleDAOImplTest extends TestCase {

	VehicleDAO vehicleDAO;

	@SuppressWarnings("resource")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DAOFactory daoFactory = new ClassPathXmlApplicationContext("springTest.xml").getBean(DAOFactory.class);
		vehicleDAO = daoFactory.getVehicleDAO();
	}

	public void testGetVehicle() {
		Vehicleinfotype vehicle = vehicleDAO.getVehicle("12000", "18MGH5673");
		assertNotNull(vehicle);
		assertEquals(vehicle.getLicplate(), "2345");
	}

}
