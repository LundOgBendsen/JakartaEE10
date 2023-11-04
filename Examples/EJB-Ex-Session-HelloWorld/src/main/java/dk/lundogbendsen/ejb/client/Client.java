package dk.lundogbendsen.ejb.client;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dk.lundogbendsen.ejb.Hello;

public class Client {
	private static final String EJB_JNDI = "EJB-Ex-Session-HelloWorld-1.0-SNAPSHOT/HelloBean!dk.lundogbendsen.ejb.Hello";
	
	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}

	
	public static void main(String[] args) throws Exception {

		System.out.println("Calling EJB...");

		 Context ctx = createInitialContext();

		Hello ejb = (Hello) ctx
				.lookup(EJB_JNDI);

		String result = ejb.sayHello("milo");

		System.out.println("Calling EJB...ok! Result: " + result);
	}
}
