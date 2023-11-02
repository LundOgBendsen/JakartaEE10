package dk.lundogbendsen.cdi;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("orderDao")
public class OrderDaoBean implements OrderDao {

	@Inject
	Event<Order> orderEvent;

	@Override
	public void createOrder(long orderNumber) {
		
		boolean successful=false;
		Order order=null;
		try {
			order = new Order(orderNumber);
			successful=true;
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new RuntimeException(e);
		}
		
		if (successful) {
			orderEvent.fire(order);
		}
	}
}