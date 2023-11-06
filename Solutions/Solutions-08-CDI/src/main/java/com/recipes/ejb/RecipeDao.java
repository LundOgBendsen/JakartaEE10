package com.recipes.ejb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.recipes.cdi.PerformanceLogging;
import com.recipes.model.Recipe;

@Stateless
@PerformanceLogging
public class RecipeDao {
	@PersistenceContext(unitName="pu")
	private EntityManager em;

	@PerformanceLogging
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Set<Recipe> findAll() {
		Query query = em.createNamedQuery("Recipe.findAllRecipesWithIngredients");
		List<Recipe> list = (List<Recipe>) query.getResultList();
		Set<Recipe> unique = new HashSet();
		unique.addAll(list);
		return unique;
	}
	
	@PerformanceLogging
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Recipe findById(long id) {
		return em.find(Recipe.class, id);		
	}
	
	@PerformanceLogging
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(Recipe recipe) {
		assert recipe.getId()==null;
		em.persist(recipe);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Recipe update(Recipe recipe) {
		assert recipe.getId()!=null;
		return em.merge(recipe);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Recipe recipe) {		
		//get a managed entity - we can't remove otherwise
		recipe = findById(recipe.getId());
		em.remove(recipe);
	}
}
