package dk.lundogbendsen.ejb;

import jakarta.ejb.Remote;

@Remote
public interface Service {
	public void startTimer();
}
