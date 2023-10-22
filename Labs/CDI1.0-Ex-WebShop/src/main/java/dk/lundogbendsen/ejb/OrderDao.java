package dk.lundogbendsen.ejb;

import jakarta.ejb.Local;

import dk.lundogbendsen.jpa.Order;
import dk.lundogbendsen.web.ShoppingCart;

@Local
public interface OrderDao {

	public Order createOrder(ShoppingCart shoppingCart);
}
