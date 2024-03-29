package com.recipes.ejb;

import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.recipes.model.Ingredient;
import com.recipes.model.MeasureUnit;
@Singleton
@Startup
public class InitializeDatabaseBean {

	Logger log = Logger.getLogger(InitializeDatabaseBean.class.getSimpleName());

	@PersistenceContext(unitName="pu")
	EntityManager em;

	@PostConstruct
	public void init() {
		Ingredient milk = getMilk();
		em.persist(milk);
		log.info("Created milk: " + milk);

		Ingredient sugar = getSugar();
		em.persist(sugar);

		Ingredient egg = getEgg();
		em.persist(egg);

		Ingredient flour = getFlour();
		em.persist(flour);

//		Recipe pancakes = getPancakeRecipe();
//
//		RecipeIngredient milkIngredient = new RecipeIngredient();
//		milkIngredient.setIngredient(milk);
//		milkIngredient.setQuantity(0.3f);
//		milkIngredient.setRecipe(pancakes);
//		pancakes.getIngredients().add(milkIngredient);
//
//		RecipeIngredient eggIngredient = new RecipeIngredient();
//		eggIngredient.setIngredient(egg);
//		eggIngredient.setQuantity(2);
//		eggIngredient.setRecipe(pancakes);
//		pancakes.getIngredients().add(eggIngredient);
//
//		RecipeIngredient flourIngredient = new RecipeIngredient();
//		flourIngredient.setIngredient(flour);
//		flourIngredient.setQuantity(2);
//		flourIngredient.setRecipe(pancakes);
//		pancakes.getIngredients().add(flourIngredient);
//		em.persist(pancakes);
//		log.info("Created pancake: " + pancakes);
//		em.flush();
//		em.clear();
//		testNamedQuery();
	}

	//	private Recipe getPancakeRecipe() {
//		Recipe recipe = new Recipe();
//		recipe.setName("Traditional pancakes");
//		recipe.setPreparationTime(30);
//		recipe.setServings(4);
//		recipe.setDescription("Blending in the flour: Put the flour and a pinch of salt into a large mixing bowl and make a well in the centre. Crack the eggs into the middle, then pour in about 50ml milk and 1 tbsp oil. Start whisking from the centre, gradually drawing the flour into the eggs, milk and oil. Once all the flour is incorporated, beat until you have a smooth, thick paste. Add a little more milk if it is too stiff to beat.");
//		return recipe;
//	}
	private Ingredient getSugar() {
		Ingredient sugar = new Ingredient();
		sugar.setName("Sugar");
		sugar.setUnit(MeasureUnit.Gr);
		sugar.setCalories(3.87f);
		return sugar;
	}

	private Ingredient getMilk() {
		Ingredient milk = new Ingredient();
		milk.setName("Milk");
		milk.setUnit(MeasureUnit.L);
		milk.setCalories(420);
		return milk;
	}

	private Ingredient getEgg() {
		Ingredient egg = new Ingredient();
		egg.setName("Egg");
		egg.setUnit(MeasureUnit.PCS);
		egg.setCalories(66);
		return egg;
	}

	private Ingredient getFlour() {
		Ingredient flour = new Ingredient();
		flour.setName("Flour");
		flour.setUnit(MeasureUnit.KG);
		flour.setCalories(3980);
		return flour;
	}

}