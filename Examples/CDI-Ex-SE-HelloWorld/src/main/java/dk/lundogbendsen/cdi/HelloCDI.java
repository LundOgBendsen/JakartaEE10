package dk.lundogbendsen.cdi;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class HelloCDI {
	public static void main(String[] args) {
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		/** disable discovery and register bean classes manually */
		try (SeContainer container = initializer.disableDiscovery().addBeanClasses(HelloCDI.class).initialize()) {
			Instance<HelloCDI> select = container.select(HelloCDI.class);
			System.out.println(select.get());
		}

		// auto load class using beans.xml.
		try (SeContainer container = initializer.initialize()) {
			Instance<HelloCDI> select = container.select(HelloCDI.class);
			System.out.println(select.get());
		}

	}

	@PostConstruct
	public void init() {
		System.out.println("Initialized...");
	}

	@Override
	public String toString() {
		return "HelloCDI[]";
	}
}