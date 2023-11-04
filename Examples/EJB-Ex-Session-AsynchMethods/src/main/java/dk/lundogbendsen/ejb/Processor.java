package dk.lundogbendsen.ejb;

import java.util.concurrent.Future;

import jakarta.ejb.Remote;

@Remote
public interface Processor {
	
	public void fireAndForget();

	public Future<String> fireAndGetResultLater();
}
