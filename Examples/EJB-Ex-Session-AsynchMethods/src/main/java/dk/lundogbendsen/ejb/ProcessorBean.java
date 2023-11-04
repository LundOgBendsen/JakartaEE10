package dk.lundogbendsen.ejb;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;

@Stateless
public class ProcessorBean implements Processor {

	@Asynchronous
	public void fireAndForget() {
		System.out.println("ProcessorBean.fireAndForget(): START");

		sleep(10);

		System.out.println("ProcessorBean.fireAndForget(): STOP");
	}

	@Asynchronous
	public Future<String> fireAndGetResultLater() {
		System.out.println("\tProcessorBean.fireAndGetResultLater(): START");

		sleep(5);

		System.out.println("\tProcessorBean.fireAndGetResultLater(): STOP");
		return new AsyncResult<String>("ProcessorBean FINISHED :)");
	}

	// -- Helper method: 'sleep'

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new EJBException(e);
		}
	}

}
