package dk.lundogbendsen.ejb;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import dk.lundogbendsen.annotations.Danish;
import dk.lundogbendsen.annotations.German;
import dk.lundogbendsen.annotations.LoggedIn;
import dk.lundogbendsen.jpa.Customer;

@RequestScoped
public class TaxCalculators {

	@Inject @Danish
	TaxCalculator danishCalculator;

	@Inject @German
	TaxCalculator germanCalculator;

	@Inject @LoggedIn
	Customer customer;

	@Produces @RequestScoped
	public TaxCalculator getTaxCalculator() {
		if (customer == null) {
			throw new IllegalStateException("No Customer active");
		}
		if (customer.isDanish()) {
			System.out.println("Using danish tax calculator");
			return danishCalculator;
		}
		System.out.println("Using german tax calculator for all non Danes");
		return germanCalculator;
	}
}
