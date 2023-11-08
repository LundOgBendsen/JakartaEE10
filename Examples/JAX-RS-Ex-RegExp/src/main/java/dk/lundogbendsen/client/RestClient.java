package dk.lundogbendsen.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class RestClient {

	public static void main(String[] args) {
		ClientBuilder cb = ClientBuilder.newBuilder();
		Client client = cb.build();
				
		Response res = client
				.target("http://localhost:8080/JAX-RS-Ex-RegExp-1.0-SNAPSHOT/resources/employees/121280-1212")
				.request("text/plain").get();
		System.out.println(res.readEntity(String.class));
		
	}
}
