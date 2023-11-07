package dk.lundogbendsen.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class RestClient {
	private static String BASE_URL = "http://localhost:8080/JAX-RS-Ex-ContentNegotiation-1.0-SNAPSHOT/resources";

	public static void main(String[] args) {
		ClientBuilder cb = ClientBuilder.newBuilder();
		Client client = cb.build();
				
		Response res = client
				.target(BASE_URL + "/courses/LB1300")
				.request("application/xml").get();
		System.out.println("As XML: " + res.readEntity(String.class));
		
		res = client
				.target(BASE_URL + "/courses/LB1300")
				.request("text/plain").get();
		System.out.println("As plain text: " + res.readEntity(String.class));
		
		res = client
				.target(BASE_URL + "/courses/LB1300")
				.request("image/gif").get();
		String gif = res.readEntity(String.class);

		System.out.println("As gif: " + gif);


		
	}
}
