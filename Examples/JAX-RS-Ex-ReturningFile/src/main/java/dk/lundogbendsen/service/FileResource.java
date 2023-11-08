package dk.lundogbendsen.service;

import java.io.File;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;


@Path("/files")
public class FileResource {
	
	@GET
	@Path("{id}")
	@Produces("text/html")
	public File getFile(@PathParam("id") String filename) {
		return new File("L:\\" + filename);
	}
	
}
