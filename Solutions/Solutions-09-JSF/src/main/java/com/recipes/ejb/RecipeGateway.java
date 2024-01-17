package com.recipes.ejb;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import com.recipes.model.Recipe;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RecipeGateway {
	@PersistenceContext(type=PersistenceContextType.EXTENDED, unitName="pu")
	EntityManager em;
	
	Recipe current;
	
	public Recipe find(long id) {
		this.current = this.em.find(Recipe.class, id);
		return this.current;
	}

	public Recipe getCurrent() {
		return current;
	}

	public void create(Recipe recipe) {
		this.em.persist(recipe);
		this.current = recipe;
	}

	
	public void remove(long id) {
		Recipe ref = this.em.getReference(Recipe.class, id);
		this.em.remove(ref);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save() {
	}
	
	@Remove
	public void closeGateway() {
	}	
}
