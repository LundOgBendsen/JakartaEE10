package dk.lundogbendsen.web;

import jakarta.enterprise.inject.Produces;

public class CdiFactory {

	@Produces @UpperCaseGreeting
	public String upper() {
		return "HELLO @QUALIFIER CDI WORLD =:)";
	}

	@Produces @LowerCaseGreeting
	public String lower() {
		return "hello @qualifier cdi world =:)";
	}
	
}
