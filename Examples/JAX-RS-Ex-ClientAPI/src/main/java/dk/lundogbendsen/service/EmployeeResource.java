package dk.lundogbendsen.service;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/employees")
public class EmployeeResource {
	
	@GET
	@Path("{first}-{last}")
	@Produces("text/plain")
	public String getEmployee(@PathParam("first") String first,@PathParam("last") String last) {
		System.out.println("GET, employee=" + first + " " + last);
		return "Found 1 employee, name=" + first + " " + last;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Employee getEmployeeEntity(@PathParam("id") String id) {
		System.out.println("GET, employee.id=" + id);
		Employee employee = new Employee();
		employee.setId(id);
		employee.setName("Roy Fielding");
		return employee;
	}
	
	@POST
	@Path("managers")
	@Consumes(MediaType.APPLICATION_XML)	
	public void addToManagers(Employee e) {
		System.out.println("Added " + e.getId() + ", " + e.getName()  + " to managers.");
	}
	
	
	
}
