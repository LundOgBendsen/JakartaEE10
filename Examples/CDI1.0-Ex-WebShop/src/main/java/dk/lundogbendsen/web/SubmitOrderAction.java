package dk.lundogbendsen.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dk.lundogbendsen.annotations.LoggedCall;
import dk.lundogbendsen.ejb.OrderDao;
import dk.lundogbendsen.jpa.Order;

@Named
@RequestScoped
public class SubmitOrderAction {

	@Inject
	OrderDao orderDao;

	@Inject
	ShoppingCart shoppingCart;
	
	@Inject
	Messages messages;

	@LoggedCall
	public void exec() {
		if (!shoppingCart.isEmpty()) {
			Order order = orderDao.createOrder(shoppingCart);
			messages.add("Order submitted: " + order);
		} else {
			messages.add("Cannot submit order when shopping cart is empty");
		}
	}
}
