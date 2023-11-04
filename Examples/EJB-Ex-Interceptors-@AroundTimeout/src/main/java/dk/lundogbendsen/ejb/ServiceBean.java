package dk.lundogbendsen.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.interceptor.Interceptors;

@Stateless
@Interceptors(MyInterceptor.class)
public class ServiceBean implements Service {

	@Resource
	private TimerService timerService;

	public void startTimer() {
		System.out.println("ServiceBean.startTimer()");
		System.out.println("\tTimer '007' starts in 2 seconds");
		TimerConfig cfg = new TimerConfig("007", true);
		timerService.createSingleActionTimer(2000, cfg);
	}

	@Timeout
	void handleTimeout(Timer timer) {
		System.out.println("ServiceBean.handleTimeout()");
		String info = timer.getInfo().toString();
		System.out.println("\tTimer.info: " + info);
	}
}
