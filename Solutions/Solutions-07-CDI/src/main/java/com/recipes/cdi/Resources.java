package com.recipes.cdi;

import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.recipes.ejb.RecipeDao;
import com.recipes.ejb.RecipeGateway;
import com.recipes.ejb.RecipeService;

@ApplicationScoped
public class Resources {

	@Produces
	@PersistenceContext(unitName="pu")
	EntityManager em;
	
	@CustomResource
	@Produces
	@EJB
	RecipeDao dao;

	@CustomResource
	@Produces
	@EJB
	RecipeService recipeService;
	
	@CustomResource
	@Produces
	@EJB
	RecipeGateway gateway;
	
	@Produces
	public Logger getLogger(InjectionPoint ip) {
		return Logger.getLogger(ip.getMember().getDeclaringClass().getSimpleName());
	}
}
