package dk.lundogbendsen.ejb;

import java.util.concurrent.TimeUnit;

import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.EJBException;
import jakarta.ejb.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MySingletonBean implements MySingleton {

	// Note: using 'synchronized' to ensure method 
	// call serialization...
	public synchronized void lockBean(String threadId, int seconds) {
		System.out.println("ENTER: " + threadId + " lockBean(" + seconds + ")");

		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new EJBException(e);
		}

		System.out.println("EXIT: " + threadId + " lockBean(" + seconds + ")");
	}

}
