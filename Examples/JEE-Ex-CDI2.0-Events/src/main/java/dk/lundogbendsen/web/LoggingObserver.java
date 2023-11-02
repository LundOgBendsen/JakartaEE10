package dk.lundogbendsen.web;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.interceptor.Interceptor;

@ApplicationScoped
public class LoggingObserver {

	public void receiveAsync(@ObservesAsync @Priority(Interceptor.Priority.APPLICATION) Order order) {
		System.out.println("LoggingObserver: Recieved an Order Event!");
	}
	
}
