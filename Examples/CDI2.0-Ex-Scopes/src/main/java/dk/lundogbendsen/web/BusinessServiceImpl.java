package dk.lundogbendsen.web;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;

// Try these - to see how the choice of scope has
// effect on the message:
// - There can only be one active scope
@ApplicationScoped
//@RequestScoped
//@SessionScoped // (Requires Serializable)
public class BusinessServiceImpl implements BusinessService, Serializable {

	//This is called twice when application scoped due to creation of proxies (by specification)
	{
		System.out.println("Instantiated: " + counter);
	}

	//this is called once when application scoped
	@PostConstruct
	void init() {
		System.out.println("PostConstruct: " + counter);
	}

	private static final long serialVersionUID = 0xCAFEBABE;

	private final static AtomicInteger counter = new AtomicInteger(0);

	static {
		System.out.println("Class loaded: " + counter);
	}

	//message is set at instantiation time

	private String message = "Hello CDI World =:) ... Counter value is: [" + counter.incrementAndGet() + "]. Try using another browser.";

	public String getMessageOfTheDay() {
		return message;
	}
}
