package dk.lundogbendsen.ejb;

import jakarta.ejb.Stateless;

import dk.lundogbendsen.annotations.Danish;
import dk.lundogbendsen.annotations.LoggedCall;

@Stateless
@Danish
public class DanishTaxCalculator implements TaxCalculator {

	@Override
	@LoggedCall
	public double calculateTaxes(double price) {
		return price * 0.25;
	}

}
