package dk.lundogbendsen.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class RestClient {
	private static final String BASE_URL = "http://localhost:8080/JAX-RS-Ex-SubresourceLocators-1.0-SNAPSHOT/resources";

	public static void main(String[] args) {
		ClientBuilder cb = ClientBuilder.newBuilder();
		Client client = cb.build();
				
		Response res = client
				.target(BASE_URL + "/courses/LB1300")
				.request("application/xml").get();
		System.out.println(res.readEntity(String.class));
		res.close();
		
		res = client
				.target(BASE_URL + "/courses/LB1300/events/0")
				.request("application/xml").get();
		System.out.println(res.readEntity(String.class));
		res.close();
		
		res = client
				.target(BASE_URL + "/courses/LB1300/events/0/students/0")
				.request("application/xml").get();
		System.out.println(res.readEntity(String.class));
		res.close();
		
	}
}
