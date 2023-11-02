package dk.lundogbendsen.cdi;

import java.util.logging.Logger;

import jakarta.enterprise.event.Observes;

public class OrderObserver {
		Logger log = Logger.getLogger(OrderObserver.class.getName());

		public void onCreate(@Observes Order order) {
			log.info("OrderObserver: Succesfully created order: #" + order.getNumber());
		}
}
