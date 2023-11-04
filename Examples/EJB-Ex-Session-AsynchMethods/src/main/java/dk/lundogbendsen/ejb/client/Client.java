package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.Processor;

public class Client {
	private static final String EJB_JNDI = "EJB-Ex-Session-AsynchMethods-1.0-SNAPSHOT/ProcessorBean!dk.lundogbendsen.ejb.Processor";
	
	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}
	
	public static void main(String[] args) throws Exception {

        final Context ctx = createInitialContext();
		Processor ejb = (Processor) ctx
				.lookup(EJB_JNDI);

		ejb.fireAndForget();
		System.out.println("Invoked 10 second job: ejb.fireAndForget()");

		Future<String> future = ejb.fireAndGetResultLater();
		System.out.println("Invoked 05 second job: ejb.fireAndGetResultLater()");

		System.out.println("Querying for result every second...");
		while (!future.isDone()) {
			System.out.println("\tQuerying for result...");
			try {
				String result = future.get(1, TimeUnit.SECONDS);
				System.out.println("Got response from ejb: " + result);
			} catch (TimeoutException to) {
				continue;
			}
		}

	}
}
