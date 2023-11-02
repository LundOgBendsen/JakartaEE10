package dk.lundogbendsen.web;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.interceptor.Interceptor;

@ApplicationScoped
public class AuditObserver {

	public void receiveAsync(@ObservesAsync @Priority(Interceptor.Priority.APPLICATION + 100) Order order) {
		if (order.getNumber() == null) {
			System.out.println("AuditObserver: Recieved an invalid Order Event. Throwing exception!");
			throw new IllegalStateException("Order number can not be null");
		}
		System.out.println("AuditObserver: Recieved an Order Event!");
	}
	
}
