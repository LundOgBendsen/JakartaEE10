package dk.university.adm.enrollment.boundary.rest;

import dk.university.adm.enrollment.boundary.Enrollment;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



import dk.university.adm.model.Student;

import java.util.logging.Logger;

@Stateless
@Path("/enrollment")
public class EnrollmentResource {
	private static Logger log = Logger.getLogger(EnrollmentResource.class.getName());
	
	//html form for enrollment
	private static final String page = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
			"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
			"<body>\r\n" + 
			"	<h1>Enrollment</h1>\r\n" +
			"	<form action=\"./enrollment\" method=\"post\">\r\n" +
			"		<table>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>First name</td>\r\n" +
			"				<td><input name=\"firstname\" /></td>\r\n" +
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Last name</td>\r\n" +
			"				<td><input name=\"lastname\" /></td>\r\n" +
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>CPR</td>\r\n" + 
			"				<td><input name=\"cpr\" /></td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td><button type=\"submit\">Enroll</button></td>\r\n" +
			"				<td><button type=\"reset\">Cancel</button></td>\r\n" +
			"			</tr>\r\n" + 
			"		</table>\r\n" + 
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>\r\n";
	
	//Boundary: default access
	@EJB
	Enrollment enrollment;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_XHTML_XML)
	public String getHTMLFormPage() {
		return page;
	}
	
	@POST
	public Response enroll(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, @QueryParam("cpr") String cpr) {
		Student student = new Student();
		student.setFirstName(firstname);
		student.setLastName(lastname);
		student.setCpr(cpr);
		enrollment.enrollStudent(student);
		log.info("Student enrolled via REST");
		return Response.status( Response.Status.ACCEPTED).build();
	}
}
