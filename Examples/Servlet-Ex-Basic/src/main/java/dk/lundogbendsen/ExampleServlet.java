package dk.lundogbendsen;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/servlet")
public class ExampleServlet extends HttpServlet {
	
	@PostConstruct
	void doInitialize() {
		System.out.println("Servlet initialized!");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			System.out.println("ExampleServlet.doGet invoked!");
			PrintWriter out = resp.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("<h1>ExampleServlet</h1>");
			out.print("<p>Time: "+System.currentTimeMillis()+"</p>");
			out.print("<p>Request uri: "+req.getRequestURI()+"</p>");
			out.print("<p>Query string: "+req.getQueryString()+"</p>");
			out.print("<p>Parameter map"+req.getParameterMap()+"</p>");
			out.print("</body>");
			out.print("</html>");
	}
}
