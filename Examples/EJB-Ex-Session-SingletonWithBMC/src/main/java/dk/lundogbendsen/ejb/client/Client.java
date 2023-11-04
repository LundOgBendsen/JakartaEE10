package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.MySingleton;

public class Client {

	private static final String EJB_JNDI = "EJB-Ex-Session-SingletonWithBMC-1.0-SNAPSHOT/MySingletonBean!dk.lundogbendsen.ejb.MySingleton";

	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}


	public static void main(String[] args) throws Exception {

		final Context ctx = createInitialContext();
		
		final MySingleton singleton = (MySingleton) ctx
				.lookup(EJB_JNDI);
		final MySingleton singleton2 = (MySingleton) ctx
				.lookup(EJB_JNDI);

		// Launch new thread to get READ lock
		new Thread(new Runnable() {
			public void run() {
				System.out.println("T1 - readLock(5) - CALL..");
				singleton.lockBean("T1", 5 /* seconds */);
				System.out.println("T1 - readLock(5) - FINISH");
			}
		}).start();

		// allow first thread to start...
		TimeUnit.SECONDS.sleep(1);

		// Launch main thread to get READ lock
		System.out.println("T2 - readLock(2) - CALL..");
		singleton2.lockBean("T2", 2 /* seconds */);
		System.out.println("T2 - readLock(2) - FINISH");
	}
}
