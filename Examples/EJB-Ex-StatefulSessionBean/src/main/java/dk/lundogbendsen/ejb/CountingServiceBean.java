package dk.lundogbendsen.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;

@Stateful
public class CountingServiceBean implements CountingService {

	private int counter;
	private String instanceName;

	public int incrementCounter() {
		if (instanceName == null) {
			String msg = "Instance name of the bean must be set before use";
			throw new IllegalStateException(msg);
		}
		return counter++;
	}

	// Client init method
	public void setInstanceName(String instanceName) {
		if (instanceName == null) {
			String msg = "Parameter [instanceName] must not be null";
			throw new NullPointerException(msg);
		}
		if (this.instanceName != null) {
			String msg = "Instance name of the bean must only be set once";
			throw new IllegalStateException(msg);
		}
		this.instanceName = instanceName;
	}

	@Remove
	public void dispose() {
	}

	@PostConstruct
	void initialization() {
		System.out.println("An instance of CountingServiceBean was created. " + "Instance name is: " + instanceName
				+ ". Counter value is: " + counter);
	}

	@PreDestroy
	void cleanup() {
		System.out.println("An instance of CountingServiceBean is being destroyed. " + "Instance name is: "
				+ instanceName + ". Counter value is: " + counter);
	}
}
