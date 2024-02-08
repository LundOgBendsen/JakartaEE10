package dk.lundogbendsen.ws;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService
@HandlerChain(file = "handlers.xml")
public class HelloWorld {
	
	@WebResult(name="Greeting")
	public String sayHello(@WebParam(name="name") String name) {
		return String.format("Hello, %s", name);
	}

}
