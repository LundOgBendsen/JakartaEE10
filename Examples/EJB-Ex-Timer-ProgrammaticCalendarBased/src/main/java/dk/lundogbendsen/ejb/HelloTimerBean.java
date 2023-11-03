package dk.lundogbendsen.ejb;

import java.util.Date;

import jakarta.annotation.Resource;
import jakarta.ejb.NoMoreTimeoutsException;
import jakarta.ejb.ScheduleExpression;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;

@Stateless
public class HelloTimerBean implements HelloTimer {

	@Resource
	private TimerService timerService;

	public void scheduleTimer(String timerName, int startAfterSec,
			int stopAfterSec) {
		System.out.println("HelloTimerBean.scheduleTimer()");
		System.out.println("\t" + new Date());

		Date start = new Date(System.currentTimeMillis() + startAfterSec * 1000);
		Date end = new Date(System.currentTimeMillis() + stopAfterSec * 1000);

		ScheduleExpression exp = new ScheduleExpression();
		exp.start(start).hour("*").minute("*").end(end);

		TimerConfig cfg = new TimerConfig(timerName, false);

		timerService.createCalendarTimer(exp, cfg);
	}

	@Timeout
	void timeout(Timer timer) {
		System.out.println("HelloTimerBean.timeout()");
		System.out.println("\tNow is: " + new Date());
		try {
			System.out.println("\tNxt is: " + timer.getNextTimeout());
		} catch (NoMoreTimeoutsException e) {
			System.out.println("\tNxt is: (no more timeouts)" );
		}
	}
}
