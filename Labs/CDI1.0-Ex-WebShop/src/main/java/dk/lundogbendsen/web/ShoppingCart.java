package dk.lundogbendsen.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dk.lundogbendsen.jpa.OrderLine;
import dk.lundogbendsen.jpa.Product;

@ConversationScoped
@Named
public class ShoppingCart implements Serializable {
	
	@Inject
	Messages messages;

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public boolean isEmpty() {
		return orderLines == null || orderLines.isEmpty();
	}


	public void add(Product p) {
		if (p != null) {
			// try to find matching product in existing orderlines
			for (OrderLine orderLine : orderLines) {
				if (p.equals(orderLine.getProduct())) {
					orderLine.setQuantity(orderLine.getQuantity() + 1);
					messages.add("Incremented quantity of " + p.getName() + " to " + orderLine.getQuantity());
					return;
				}
			}

			// no similar products exists
			OrderLine o = new OrderLine();
			o.setProduct(p);
			o.setQuantity(1);
			orderLines.add(o);
			messages.add("Added a " + p.getName() + " to the cart");
		}
	}
}
