package dk.lundogbendsen.web;

import java.util.concurrent.CompletionStage;

public interface BusinessService {

	public CompletionStage<Order> createOrder();

	public CompletionStage<Order> createInvalidOrder();
	
}
