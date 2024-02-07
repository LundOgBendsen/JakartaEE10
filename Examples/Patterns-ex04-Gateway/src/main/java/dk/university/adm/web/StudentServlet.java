package dk.university.adm.web;

import java.io.IOException;
import java.io.PrintWriter;

import dk.university.adm.model.Student;
import dk.university.adm.model.Subject;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dk.university.adm.enrollment.boundary.StudentGateway;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    @SessionScoped
	StudentGateway gw;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			Student s = gw.getCurrent();
			if (s==null) {
				s = gw.find(1);
			}
			Subject subject = new Subject();
			subject.setEctsPoints(7.5);
			subject.setCampus("Campus 1");
			subject.setName("Class " + System.currentTimeMillis());
			subject.getStuderende().add(s);
			s.getSubject().add(subject);
			out.print("<p>Student + " + s.getFirstName() + " has " + s.getSubject().size() + " subject(s).</p>" );
			if (s.getSubject().size() % 10==0) {
				out.print("<p>Saving to databasen!</p>" );
				gw.save();			
			}
			out.println("<p>Press F5 to refresh and generate another class. Saving for every 10th subject.</p></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
