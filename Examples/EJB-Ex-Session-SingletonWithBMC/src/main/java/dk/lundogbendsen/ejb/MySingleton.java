package dk.lundogbendsen.ejb;

import jakarta.ejb.Remote;

@Remote
public interface MySingleton {
	public void lockBean(String threadId, int seconds);
}
