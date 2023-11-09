package dk.universitet.adm.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dk.universitet.adm.indskrivning.boundary.StuderendeGateway;
import dk.universitet.adm.model.Fag;
import dk.universitet.adm.model.Studerende;

@WebServlet("/StuderendeServlet")
public class StuderendeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    @SessionScoped
    StuderendeGateway gw;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			Studerende s = gw.getCurrent();
			if (s==null) {
				s = gw.find(1);
			}
			Fag fag = new Fag();
			fag.setEctspoints(7.5);
			fag.setCampus("Campus 1");
			fag.setNavn("Class " + System.currentTimeMillis());
			fag.getStuderende().add(s);
			s.getFag().add(fag);
			out.print("<p>Student + " + s.getFornavn() + " has " + s.getFag().size() + " class.</p>" );
			if (s.getFag().size() % 10==0) {
				out.print("<p>Saving to databasen!</p>" );
				gw.save();			
			}
			out.println("<p>Press F5 to refresh and generate another class. Saving for every 10th classes.</p></body></html>");			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
