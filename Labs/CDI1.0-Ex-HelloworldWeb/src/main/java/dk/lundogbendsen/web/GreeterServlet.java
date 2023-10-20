package dk.lundogbendsen.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class GreeterServlet extends HttpServlet {

	private static final long serialVersionUID = 0xCAFEBABE;
	
	@Inject 
	private BusinessService service;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.printf("<html><body>%s</body></html>", service.getMessageOfTheDay());
	}
}