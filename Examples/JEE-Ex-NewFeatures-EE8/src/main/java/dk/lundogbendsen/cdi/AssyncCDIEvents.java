package dk.lundogbendsen.cdi;

import java.util.concurrent.CompletionStage;

import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.NotificationOptions;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;

public class AssyncCDIEvents {

	@Inject
	Event<String> fireAlarm;

	@Resource
	ManagedExecutorService threadPool;

	@Schedule(second = "*/5", minute = "*", hour = "*")
	public void send() {
		String event = "fire " + System.currentTimeMillis();
		CompletionStage<String> completion = this.fireAlarm.fireAsync(event,
				NotificationOptions.ofExecutor(threadPool)); // returns immediately
		completion.thenAccept(this::eventDelivered);
	}

	void eventDelivered(String event) {
		System.out.println("Event delivered" + event);
	}
	
	public void onFireNotification(@ObservesAsync String event) {
		System.out.println("Event recived" + event);
    }
	
	
}
