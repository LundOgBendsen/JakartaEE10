package dk.universitet.adm.indskrivning.boundary.rest;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// import org.jboss.logging.Logger;
import java.util.logging.Logger;

import dk.universitet.adm.indskrivning.boundary.Indskrivning;
import dk.universitet.adm.model.Studerende;

@Stateless
@Path("/indskrivning")
public class IndskrivningResource {
	private static Logger log = Logger.getLogger(IndskrivningResource.class.getName());
	
	//html med en form til indskrivning
	private static final String page = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
			"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
			"<body>\r\n" + 
			"	<h1>Indskrivning</h1>\r\n" + 
			"	<form action=\"./indskrivning\" method=\"post\">\r\n" + 
			"		<table>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Fornavn</td>\r\n" + 
			"				<td><input name=\"fornavn\" /></td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Efternavn</td>\r\n" + 
			"				<td><input name=\"efternavn\" /></td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>CPR</td>\r\n" + 
			"				<td><input name=\"cpr\" /></td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td><button type=\"submit\">Indskriv</button></td>\r\n" + 
			"				<td><button type=\"reset\">Annuller</button></td>\r\n" + 
			"			</tr>\r\n" + 
			"		</table>\r\n" + 
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>\r\n";
	
	@EJB
	Indskrivning productService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_XHTML_XML)
	public String getHTMLFormPage() {
		return page;
	}
	
	@POST
	public Response indskriv(@QueryParam("fornavn") String fornavn, @QueryParam("efternavn") String efternavn, @QueryParam("cpr") String cpr) {
		Studerende studerende = new Studerende();
		studerende.setFornavn(fornavn);
		studerende.setEfternavn(efternavn);
		studerende.setCprnummer(cpr);
		productService.indskrivStuderende(studerende);
		log.info("Studerende indskrevet via REST");
		return Response.status( Response.Status.ACCEPTED).build();
	}
}
