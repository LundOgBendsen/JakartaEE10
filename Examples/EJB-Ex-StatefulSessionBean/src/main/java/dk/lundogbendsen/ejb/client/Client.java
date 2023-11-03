package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.CountingService;

public class Client {

	public static void main(String[] args) throws Exception {

		System.out.println("Calling EJB...");

		int noOfThreads = 200;
		int noOfCallsPerThread = 5;

		for (int n = 0; n < noOfThreads; n++) {
			ClientThread clientThread = new ClientThread(noOfCallsPerThread, "Client-" + n);
			clientThread.start();
		}
	}
}


class ClientThread extends Thread {
	int noOfCallsToMake;
	private String clientName;

	public ClientThread(int noOfCallsToMake, String clientName) {
		this.noOfCallsToMake = noOfCallsToMake;
		this.clientName = clientName;
	}

	private static final String EJB_JNDI = "EJB-Ex-StatefulSessionBean-1.0-SNAPSHOT/CountingServiceBean!dk.lundogbendsen.ejb.CountingService";

	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}




	@Override
	public void run() {
		try {
	
	        final Context ctx = createInitialContext();
			
			CountingService countingService = (CountingService) ctx.lookup(EJB_JNDI);

			countingService.setInstanceName(clientName);

			for (int n = 0; n < noOfCallsToMake; n++) {
				System.out.println(clientName + ".counter = " + countingService.incrementCounter());
				Thread.yield();
			}

			countingService.dispose();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
