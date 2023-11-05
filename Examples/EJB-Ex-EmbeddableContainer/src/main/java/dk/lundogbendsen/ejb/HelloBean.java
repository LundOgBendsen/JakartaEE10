package dk.lundogbendsen.ejb;

import jakarta.ejb.Stateless;

@Stateless
public class HelloBean implements Hello {

	public String sayHello(String name) {
		System.out.println("HelloBean.sayHello("+name+")");
		return "Hello " + name;
	}

}
