package dk.lundogbendsen.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;


@Path("/employees")
public class EmployeeResource {
	
	@GET
	@Path("{first}-{last}")
	@Produces("text/plain")
	public String getEmployee(@PathParam("first") String first,@PathParam("last") String last) {
		System.out.println("GET, employee=" + first + " " + last);
		return "Found 1 employee, name=" + first + " " + last;
	}
	
}
