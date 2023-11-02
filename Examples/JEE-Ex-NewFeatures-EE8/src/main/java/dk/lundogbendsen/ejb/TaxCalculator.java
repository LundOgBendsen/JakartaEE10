package dk.lundogbendsen.ejb;

import jakarta.ejb.Local;

@Local
public interface TaxCalculator {

	public double calculateTaxes(double price);
}
