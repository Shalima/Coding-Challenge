package client;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import jaxb.MitchellClaimType;
import model.Claim;

/**
 * WebServiceClient class represents the client for the webservice.
 * 
 * @author Shalima
 *
 */
public class WebServiceClient {

	public List<Claim> getAllClaims() {
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/org.mitchell.claim/resources/claims");

		ClientResponse response = webResource.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		return response.getEntity(new GenericType<List<Claim>>() {
		});
	}

	public String createClaim(MitchellClaimType mitchellClaimType) {
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/org.mitchell.claim/resources/claims/create");

		ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, mitchellClaimType);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		return response.getEntity(String.class);
	}

	public String updateClaim(MitchellClaimType mitchellClaimType) {
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/org.mitchell.claim/resources/claims/update");

		ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, mitchellClaimType);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		return response.getEntity(String.class);
	}

	public String deleteClaim(String claimId) {
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/org.mitchell.claim/resources/claims/deleteClaim");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("claimId", claimId);

		ClientResponse response = webResource.queryParams(queryParams).get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		return response.getEntity(String.class);

	}

	public Claim getClaim(String claimId) {
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8181/RestJersey/webresources/myresource/findClaimByClaimId");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("claimId", claimId);

		ClientResponse response = webResource.queryParams(queryParams).get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		return response.getEntity(Claim.class);

	}

	public List<Claim> getClaimByDateRange(String startDate, String endDate) {
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/org.mitchell.claim/resources/claims/GetInDateRange");

		//adding the query params
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();

		queryParams.add("startDate", startDate);
		queryParams.add("endDate", endDate);

		ClientResponse response = webResource.queryParams(queryParams).get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		return response.getEntity(new GenericType<List<Claim>>() {
		});

	}

}
