package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.HelloTimer;

public class Client {
	private static final String EJB_JNDI = "EJB-Ex-Timer-ProgrammaticCalendarBased-1.0-SNAPSHOT/HelloTimerBean!dk.lundogbendsen.ejb.HelloTimer";

	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}

	public static void main(String[] args) throws Exception {

		final Context ctx = createInitialContext();

		HelloTimer ejb = (HelloTimer) ctx
				.lookup(EJB_JNDI);

		int startAfterSec = 5;
		int stopAfterSec = 60;
		ejb.scheduleTimer("mytimer", startAfterSec, stopAfterSec);

		System.out.println("Timer has been successfully scheduled");
	}
}
