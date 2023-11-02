package dk.lundogbendsen.ejb;

import jakarta.ejb.Stateless;

import dk.lundogbendsen.annotations.German;
import dk.lundogbendsen.annotations.LoggedCall;

@Stateless
@German
public class GermanTaxCalculator implements TaxCalculator {

	@Override
	@LoggedCall
	public double calculateTaxes(double price) {
		return price * 0.15;
	}

}
