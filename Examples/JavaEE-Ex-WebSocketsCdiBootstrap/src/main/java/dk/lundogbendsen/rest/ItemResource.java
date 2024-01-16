package dk.lundogbendsen.rest;

import java.util.Random;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import dk.lundogbendsen.model.StockItem;
import dk.lundogbendsen.service.StockUnitService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/items")
public class ItemResource {
	@Inject
	StockUnitService service;

	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public String createItem() {
		Random random = new Random();
		service.create(new StockItem(random.nextInt()+"", "Name"));
		return "{\"status\": \"success\"}";
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String findItems() {
		return service.findAll().toString();		
	}
}
