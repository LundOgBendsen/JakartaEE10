package dk.lundogbendsen.web;

import jakarta.enterprise.inject.Produces;

public class CdiFactory {

	@Produces
	public String message() {
		return "Hello CDI MethodLoggerInterceptor World =:)";
	}
	
}
