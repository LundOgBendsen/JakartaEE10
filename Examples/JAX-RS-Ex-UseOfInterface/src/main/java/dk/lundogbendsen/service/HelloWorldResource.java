package dk.lundogbendsen.service;


/**
 * Note the lack of annotations. These are provided by the interface HelloWorld.
 * 
 */
public class HelloWorldResource implements HelloWorld {
	
	public String helloWorld(String name) {
		return "Hello, " +  name;
	}
	
}
