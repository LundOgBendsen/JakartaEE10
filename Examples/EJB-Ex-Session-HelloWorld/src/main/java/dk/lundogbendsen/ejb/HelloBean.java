package dk.lundogbendsen.ejb;

import java.util.logging.Logger;

import jakarta.ejb.Stateless;

@Stateless
public class HelloBean implements Hello {
	private Logger log = Logger.getLogger(HelloBean.class.getName());
	
	public String sayHello(String name) {

		log.warning("HelloBean.sayHello("+name+")");
		System.out.println("HelloBean.sayHello("+name+")");
		return "Hello " + name;
	}

}
