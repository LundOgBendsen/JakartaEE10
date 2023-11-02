package dk.lundogbendsen.jaxrx;

import java.util.concurrent.CompletionStage;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
public class TestClient {

	private Client client;
	private WebTarget testtarget;

	@PostConstruct
	public void init() {
		this.client = ClientBuilder.newBuilder().build();
		this.testtarget = this.client.target("https://kursusportal.it");
	}
	
	public CompletionStage<Response> fetchContent() {
		return this.testtarget.request(MediaType.TEXT_HTML).rx().get();
	}
}
