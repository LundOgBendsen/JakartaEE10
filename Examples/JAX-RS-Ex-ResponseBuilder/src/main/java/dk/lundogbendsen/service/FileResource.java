package dk.lundogbendsen.service;

import java.io.File;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;


@Path("/files")
public class FileResource {
	
	@GET
	public Response get(@QueryParam("name") String filename) {
		System.out.println("Requesting " + filename);
		File f = new File("L:\\workspace\\eclipse-workspace\\JAX-RS-Ex-ResponseBuilder\\WebContent\\" + filename);
		if (f.getName().endsWith("xml")) {
			return Response.ok(f).type(MediaType.APPLICATION_XML).build();			
		}
		if (f.getName().endsWith("html")) {
			return Response.ok(f).type(MediaType.TEXT_HTML).build();			
		}
			ResponseBuilder responseBuilder = Response.status(Status.NOT_FOUND);
		return responseBuilder.entity("No file with name " + filename).build();	
	}
}
