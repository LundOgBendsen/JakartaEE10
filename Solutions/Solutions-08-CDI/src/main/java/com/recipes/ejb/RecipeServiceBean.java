package com.recipes.ejb;

import java.util.Set;

import jakarta.ejb.Stateless;

import com.recipes.model.Recipe;
import com.recipes.model.RecipeIngredient;

@Stateless
public class RecipeServiceBean implements RecipeService {

	@Override
	public double calculateCalories(Recipe recipe) {
		Set<RecipeIngredient> ingredients = recipe.getIngredients();
		double result=0.0;
		for (RecipeIngredient ri : ingredients) {
			result+=ri.getQuantity()*ri.getIngredient().getCalories();
		}
		return result;
	}
}
