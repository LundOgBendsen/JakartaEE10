package dk.lundogbendsen.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/employees")
public class EmployeeResource {


	@GET
	@Path("{cpr: \\d{6}-\\d{4}}")
	@Produces("text/plain")
	public String getEmployee(@PathParam("cpr") String cpr) {
		System.out.println("GET, cpr=" + cpr);
		return "Found 1 employee, cpr=" + cpr;
	}
}
