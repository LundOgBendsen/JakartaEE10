package dk.lundogbendsen.ejb;

import jakarta.ejb.Remote;

@Remote
public interface CountingService {
	void setInstanceName(String instanceName);

	int incrementCounter();

	void dispose();
}