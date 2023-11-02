package dk.lundogbendsen.jaxrx;

import java.util.concurrent.CompletionStage;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Response;

import dk.lundogbendsen.jpa.Customer;

@Path("jsonb")
public class CustomerResource {

	@Inject
	TestClient client;
	
	@GET
	public String getCostumer() {
		return new Customer("Per", "DK").toJson();
	}
	
	@Path("rx")
	@GET
	public void rx(@Suspended AsyncResponse response) {
		CompletionStage<Void> thenAccept = client.fetchContent().thenAccept(this::extract).thenAccept(response::resume);
		
	}
	
	String extract(Response response) {
		return response.readEntity(String.class);
	}
}
