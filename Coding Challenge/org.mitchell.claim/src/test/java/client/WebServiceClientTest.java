package client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.xml.sax.SAXException;

import jaxb.CauseOfLossCode;
import jaxb.LossInfoType;
import jaxb.MitchellClaimType;
import jaxb.StatusCode;
import jaxb.VehicleInfoType;
import jaxb.VehicleListType;
import junit.framework.TestCase;
import model.Claim;

public class WebServiceClientTest extends TestCase {

	public void testCreateClaimClientService() throws JAXBException, SAXException, DatatypeConfigurationException {
		WebServiceClient client = new WebServiceClient();
		assertEquals(client.createClaim(getJAXBClass()), "SUCCESS");
	}

	public void testUpdateClaimClientService() {
		WebServiceClient client = new WebServiceClient();
		//create new jaxb
		MitchellClaimType claimJAXB = new MitchellClaimType();
		claimJAXB.setClaimNumber("10000");
		claimJAXB.setClaimantFirstName("Bush");
		VehicleInfoType vehicleInfo = new VehicleInfoType();
		vehicleInfo.setVin("123445778");
		vehicleInfo.setExteriorColor("Green");
		List<VehicleInfoType> vehicleList = new ArrayList<>();
		vehicleList.add(vehicleInfo);
		VehicleListType vehicles = new VehicleListType();
		vehicles.setVehicleDetails(vehicleList);
		claimJAXB.setVehicles(vehicles);

		assertEquals(client.updateClaim(claimJAXB), "SUCCESS");
	}

	public void testDeleteClaim() {
		WebServiceClient client = new WebServiceClient();
		assertEquals(client.deleteClaim("12000"), "SUCCESS");
	}

	public void getClaim() {

		WebServiceClient client = new WebServiceClient();
		Claim claim = client.getClaim("12000");
		assertEquals(claim.getClaimnumber(), "12000");
	}

	public void testGetByDateRange() {
		fail("Not yet implemented");
	}

	private MitchellClaimType getJAXBClass() throws DatatypeConfigurationException {
		MitchellClaimType claimJAXB = new MitchellClaimType();
		claimJAXB.setClaimNumber("10000");
		claimJAXB.setClaimantFirstName("Bush");
		claimJAXB.setLossDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
		claimJAXB.setStatus(StatusCode.OPEN);

		LossInfoType lossInfo = new LossInfoType();
		lossInfo.setCauseOfLoss(CauseOfLossCode.COLLISION);
		claimJAXB.setLossInfo(lossInfo);

		VehicleInfoType vehicleInfo = new VehicleInfoType();
		vehicleInfo.setVin("123445778");
		vehicleInfo.setLicPlate("1234");
		vehicleInfo.setExteriorColor("Orange");
		List<VehicleInfoType> vehicleList = new ArrayList<>();
		vehicleList.add(vehicleInfo);
		VehicleListType vehicles = new VehicleListType();
		vehicles.setVehicleDetails(vehicleList);
		claimJAXB.setVehicles(vehicles);

		return claimJAXB;
	}
}
