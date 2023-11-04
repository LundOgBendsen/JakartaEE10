package dk.lundogbendsen.ejb;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import jakarta.ejb.AccessTimeout;
import jakarta.ejb.EJBException;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;

@Singleton
public class MySingletonBean implements MySingleton {
	Logger log = Logger.getLogger(MySingleton.class.getName());
	
	@Lock(LockType.WRITE)
	public String writeLock(String threadId, int seconds) {
		log.info("writeLock");
		System.out.println("ENTER: " + threadId + " writeLock(" + seconds + ")");
		sleep(seconds);
		System.out.println("EXIT: " + threadId + " writeLock(" + seconds + ")");
		return "Success";
	}

	@Lock(LockType.READ)
	public String readLock(String threadId, int seconds) {
		log.info("readLock");
		System.out.println("ENTER: " + threadId + " readLock(" + seconds + ")");
		sleep(seconds);
		System.out.println("EXIT: " + threadId + " readLock(" + seconds + ")");
		return "Success";
	}

	@Lock(LockType.READ)
	@AccessTimeout(value = 1, unit = TimeUnit.SECONDS)
	public String readLockWithTimeout(String threadId, int seconds) {
		System.out.println("ENTER: " + threadId + " readLockWithTimeout(" + seconds + ")");
		sleep(seconds);
		System.out.println("EXIT: " + threadId + " readLockWithTimeout(" + seconds + ")");
		return "Success";
	}

	void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new EJBException(e);
		}
	}

}
