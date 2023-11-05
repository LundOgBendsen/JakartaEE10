package dk.lundogbendsen.cdi;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class Main {
	@Inject
	OrderDao orderDao;

	public static void main(String[] args) {
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		// initialize container 
		SeContainer container = initializer.initialize();

		//look up bean
		Instance<Main> select = container.select(Main.class);
		Main main = select.get();
		
		//invoke business logic that will fire events. See console. 
		main.orderDao.createOrder(1_000_000);
		main.orderDao.createOrder(1_000_001);
		main.orderDao.createOrder(1_000_002);

	}

}