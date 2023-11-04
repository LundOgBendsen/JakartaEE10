package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.Service;

public class Client {
	private static final String EJB_JNDI = "EJB-Ex-Interceptors-AroundTimeout-1.0-SNAPSHOT/ServiceBean!dk.lundogbendsen.ejb.Service";

	
	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}

	public static void main(String[] args) throws Exception {
        final Context context = createInitialContext();
		Service ejb = (Service) context
				.lookup(EJB_JNDI);

		
		ejb.startTimer();
		System.out.println("Timer scheduled");
	}

}
