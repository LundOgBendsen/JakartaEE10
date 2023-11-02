package dk.lundogbendsen.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 0xCAFEBABE;
	
	@Inject 
	private BusinessService service;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		CompletionStage<Order> cs = null;
		String error = request.getParameter("error");
		if (error!=null && "true".equals(error))	{
			System.out.println("Creating invalid order;");
			cs = service.createInvalidOrder();
		}
		
		cs = service.createOrder();
		Order order = null;
		try {
			order = cs.toCompletableFuture().get();
			out.printf("<html><body>Created order: %s. See console.</body></html>", order);
		} catch (InterruptedException | ExecutionException e) {
			out.printf("<html><body>Failed: %s. See console.</body></html>", e.getMessage());			
		}
	}
}