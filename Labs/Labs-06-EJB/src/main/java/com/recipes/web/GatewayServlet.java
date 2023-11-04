package com.recipes.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.recipes.model.Category;
import com.recipes.model.Ingredient;
import com.recipes.model.MeasureUnit;
import com.recipes.model.Recipe;
import com.recipes.model.RecipeIngredient;

@WebServlet("/GatewayServlet")
public class GatewayServlet extends HttpServlet {
	
//	@EJB
//	RecipeGateway gateway;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>Creating bread recipe via Gateway</h1>");
		Recipe bread = new Recipe();
		bread.setName("Wheat Bread");
		bread.setPreparationTime(120);
		bread.setCategory(Category.LUNCH);
		bread.setServings(10);
		
		RecipeIngredient riWater = new RecipeIngredient();
		riWater.setIngredient(getWater());
		riWater.setQuantity(1);
		bread.getIngredients().add(riWater);
		
		RecipeIngredient riYaest = new RecipeIngredient();
		riYaest.setIngredient(getYaest());
		riYaest.setQuantity(2);
		bread.getIngredients().add(riYaest);
		
		System.out.println("Start building recipe via gateway");
//		gateway.create(bread);
//		gateway.getCurrent().getIngredients().add(riWater);
//		riWater.setRecipe(bread);
//		gateway.getCurrent().getIngredients().add(riYaest);
//		riYaest.setRecipe(bread);
//		gateway.save();
		System.out.println("Finished building recipe via gateway");
		out.print("saved");		
		out.print("</body>");		
		out.print("</html>");
	}
	
	private Ingredient getWater() {
		Ingredient water = new Ingredient();
		water.setName("Water");
		water.setUnit(MeasureUnit.L);
		water.setCalories(0);
		return water;
	}
	
	private Ingredient getYaest() {
		Ingredient yaest = new Ingredient();
		yaest.setName("Yaest");
		yaest.setUnit(MeasureUnit.Gr);
		yaest.setCalories(2);
		return yaest;
	}
	
}
