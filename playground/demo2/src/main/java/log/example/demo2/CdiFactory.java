package log.example.demo2;

import jakarta.enterprise.inject.Produces;

public class CdiFactory {

	@Produces
	public String message() {
		return "Hello CDI World =:)";
	}
	
}
