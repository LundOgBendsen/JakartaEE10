package dk.lundogbendsen.web;

import java.util.concurrent.CompletionStage;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

public class BusinessServiceImpl implements BusinessService {
	
	@Inject
	Event<Order> createOrderEvent;
	
	@Override
	public CompletionStage<Order> createOrder() {
		Order order = new Order();
		System.out.println("Created order: " + order);
		return createOrderEvent.fireAsync(order);	
	}
	
	@Override
	public CompletionStage<Order> createInvalidOrder() {
		Order order = new Order();
		order.setNumber(null);
		System.out.println("Created invalid order: " + order);
		return createOrderEvent.fireAsync(order).handle((order2, ex) -> {
			if (order2 != null ) {
				return order2;
			} else {
				for (Throwable t : ex.getSuppressed()) {
					System.out.println("Suppressing: " + t);}
				 return order;			
			}			
		});	
	}
}
