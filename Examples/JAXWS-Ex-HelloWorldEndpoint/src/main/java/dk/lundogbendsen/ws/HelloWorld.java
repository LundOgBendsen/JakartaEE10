package dk.lundogbendsen.ws;


import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService
public class HelloWorld {

	@WebResult(name="greeting")
	public String sayHello(@WebParam(name="name") String name) {
		return "Hello, " + name;
	}
}
