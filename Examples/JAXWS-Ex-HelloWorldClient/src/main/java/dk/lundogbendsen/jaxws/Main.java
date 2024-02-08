package dk.lundogbendsen.jaxws;


import dk.lundogbendsen.ws.HelloWorld;
import dk.lundogbendsen.ws.HelloWorldService;

public class Main {

	public static void main(String[] args) {
		
		HelloWorldService service = new HelloWorldService();
				
		HelloWorld port = service.getHelloWorldPort();
		
		String result = port.sayHello("Milo");
		
		System.out.println(result);		
	}
}
