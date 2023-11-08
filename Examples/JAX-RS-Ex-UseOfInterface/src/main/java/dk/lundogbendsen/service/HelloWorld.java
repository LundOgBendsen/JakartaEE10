package dk.lundogbendsen.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/helloworld")
public interface HelloWorld {

	@GET
	@Path("{id}")
	@Produces("text/html")
	public abstract String helloWorld(@PathParam("id") String name);

}