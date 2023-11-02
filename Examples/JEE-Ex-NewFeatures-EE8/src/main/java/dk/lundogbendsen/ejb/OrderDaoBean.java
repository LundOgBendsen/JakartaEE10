package dk.lundogbendsen.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.lundogbendsen.annotations.LoggedIn;
import dk.lundogbendsen.jpa.Customer;
import dk.lundogbendsen.jpa.Order;
import dk.lundogbendsen.web.ShoppingCart;

@Stateless
public class OrderDaoBean implements OrderDao {

	@Inject
	@LoggedIn
	Customer customer;

	@Inject
	TaxCalculator taxCalculator;

	
	@PersistenceContext
	EntityManager em;

	public Order createOrder(ShoppingCart shoppingCart) {
		Order o = new Order(customer, shoppingCart.getOrderLines());

		double priceBeforeTax = o.calculateTotalPriceBeforeTax();
		o.setTotalPriceBeforeTax(priceBeforeTax);

		double taxes = taxCalculator.calculateTaxes(priceBeforeTax);
		double priceAfterTax = priceBeforeTax + taxes;
		o.setTotalPriceAfterTax(priceAfterTax);

		return o;
	}
}
