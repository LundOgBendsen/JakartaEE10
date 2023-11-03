package dk.lundogbendsen.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class HelloTimerBean {

	@PostConstruct
	private void init() {
		System.out.println("created");
		timeout();
	}
	
	//fires once a minute on second 1
	@Schedule(second = "1", hour="*", minute="*", info = "hellotimer", persistent=false)
	public void timeout() {
		System.out.println("HelloTimerBean.timeout()");
	}
}
