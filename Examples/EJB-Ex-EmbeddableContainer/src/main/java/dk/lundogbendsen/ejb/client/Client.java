package dk.lundogbendsen.ejb.client;

import jakarta.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import dk.lundogbendsen.ejb.Hello;

public class Client {

	public static void main(String[] args) throws Exception {
		System.out.println("Calling EJB...");
		System.setProperty("jboss.home", "L:/appservers/wildfly-29.0.1.Final");
		System.setProperty("jboss.dir", "L:/appservers/wildfly-29.0.1.Final");

		System.setProperty("org.jboss.as.embedded.ejb3.BARREN", "true");
		
		 EJBContainer container = EJBContainer.createEJBContainer();
		 Context ctx = container.getContext();
	      // Note that global naming isn't working yet.
		 
	     
	      Hello bean = (Hello) ctx.lookup("java:module/HelloBean!dk.lundogbendsen.ejb.Hello");
	      String actual = bean.sayHello("milo");
	      container.close();

		System.out.println("Calling EJB...ok! Result: " + actual);
	}
}
