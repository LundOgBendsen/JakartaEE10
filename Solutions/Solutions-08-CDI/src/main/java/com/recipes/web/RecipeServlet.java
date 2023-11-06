package com.recipes.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Set;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.recipes.cdi.CustomResource;
import com.recipes.ejb.RecipeDao;
import com.recipes.ejb.RecipeService;
import com.recipes.model.Recipe;

@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
	@Inject
	@CustomResource	
	RecipeDao dao;
	
	@Inject
	@CustomResource
	RecipeService service;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Set<Recipe> all = dao.findAll();
		DecimalFormat f = new DecimalFormat("#,###,###,##0.00");
		
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>Calories table</h1>");
		out.print("<table border='1'>");
		for (Recipe recipe : all) {
			out.print("<th>Recipe</th>");
			out.print("<th>Calories</th>");

			out.print("<tr>");
				out.print("<td>");
					out.print(recipe.getName());
				out.print("</td>");
				out.print("<td>");
					out.print(f.format(service.calculateCalories(recipe)));
				out.print("</td>");			
			out.print("</tr>");
		}
		out.print("</table>");		
		out.print("</body>");		
		out.print("</html>");
	}
}
