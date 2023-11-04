package dk.lundogbendsen.ejb;

import jakarta.ejb.Remote;

@Remote
public interface Hello {
	public String sayHello(String name);
}
