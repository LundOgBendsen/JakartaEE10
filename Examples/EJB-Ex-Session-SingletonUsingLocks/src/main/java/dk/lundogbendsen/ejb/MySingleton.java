package dk.lundogbendsen.ejb;

import jakarta.ejb.Remote;

@Remote
public interface MySingleton {

	public String writeLock(String threadId, int seconds);

	public String readLock(String threadId, int seconds);

	public String readLockWithTimeout(String threadId, int seconds);
}
