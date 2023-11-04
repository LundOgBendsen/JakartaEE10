package com.recipes.ejb;

import jakarta.ejb.Local;

import com.recipes.model.Recipe;

@Local
public interface RecipeService {

	public abstract double calculateCalories(Recipe recipe);

}