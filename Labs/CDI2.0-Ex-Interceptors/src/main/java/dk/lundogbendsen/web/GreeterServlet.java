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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Fun bonus ;).... :
		//
		// The actual class injected into the 'service' member changes
		// if you enable/disable CDI interception!
		//
		// If the CDI interceptor 'catches' methods in 'service' then
		// 'service' will be a dynamically generated CDI proxy class.
		//
		// If it doesn't then it is what you expect: "BusinessServiceImpl".
		//
		// (this is how the 'magic' works - a bit advanced proxying mechanism)
		//
		// Uncomment to see it yourself:
		System.out.println(service.getClass());

		out.printf("<html><body>%s</body></html>", service.getMessageOfTheDay());
	}
}