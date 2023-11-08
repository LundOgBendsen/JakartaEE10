package dk.lundogbendsen.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestClient {

	public static void main(String[] args) {
		ClientBuilder cb = ClientBuilder.newBuilder();
		Client client = cb.build();

		Response res = client
				.target("http://localhost:8080/JAX-RS-Ex-ResponseBuilder/resources/files?name=some.xml")
				.request().get();
		System.out.println(res.getMediaType().toString()
				.equals(MediaType.APPLICATION_XML));
		System.out.println(res.readEntity(String.class));
		res.close();

		res = client
				.target("http://localhost:8080/JAX-RS-Ex-ResponseBuilder/resources/files?name=index.html")
				.request().get();

		System.out.println(res.getMediaType());

		System.out.println(res.getMediaType().toString()
				.equals(MediaType.TEXT_HTML));

		System.out.println(res.readEntity(String.class));
		res.close();
	}
}
