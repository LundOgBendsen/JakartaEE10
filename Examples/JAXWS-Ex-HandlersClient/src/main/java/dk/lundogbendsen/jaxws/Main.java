package dk.lundogbendsen.jaxws;

import dk.lundogbendsen.ws.HelloWorld;
import dk.lundogbendsen.ws.HelloWorldService;

public class Main {

	public static void main(String[] args) {

		HelloWorldService service = new HelloWorldService();

		HelloWorld port = service.getHelloWorldPort();

		String result = port.sayHello("milo crap damn fool thilde");

		System.out.println(result); // output has xxx instead of crap,...
	}
}
