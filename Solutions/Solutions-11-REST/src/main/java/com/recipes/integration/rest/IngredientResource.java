package com.recipes.integration.rest;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.recipes.cdi.CustomResource;
import com.recipes.ejb.IngredientDao;
import com.recipes.integration.rest.json.IngredientDto;
import com.recipes.model.Ingredient;

@Path("/ingredients")
public class IngredientResource {
	
	@CustomResource
	@Inject
	IngredientDao dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<IngredientDto> getIngredients() {
		List<Ingredient> all = dao.findAll();		
		return all.stream().map(IngredientDto::new).collect(Collectors.toList());
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public IngredientDto getIngredients(@PathParam("id") Long id) {
		return new IngredientDto(dao.findById(id));
	}
	
	
}
