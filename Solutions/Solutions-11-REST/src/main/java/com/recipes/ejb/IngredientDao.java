package com.recipes.ejb;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import com.recipes.model.Ingredient;

@Stateless
public class IngredientDao {
	@Inject
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Ingredient> findAll() {
		Query query = em.createNamedQuery("Ingredient.findAll");
		List<Ingredient> list = (List<Ingredient>) query.getResultList();
		return list;
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Ingredient findById(long id) {
		return em.find(Ingredient.class, id);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(Ingredient ingredient) {
		assert ingredient.getId()==null;
		em.persist(ingredient);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Ingredient update(Ingredient ingredient) {
		assert ingredient.getId()!=null;
		return em.merge(ingredient);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Ingredient ingredient) {		
		//get a managed entity - we can't remove otherwise
		ingredient = findById(ingredient.getId());
		em.remove(ingredient);
	}
	
}
