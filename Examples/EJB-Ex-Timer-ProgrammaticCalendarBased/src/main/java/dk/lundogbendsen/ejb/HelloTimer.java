package dk.lundogbendsen.ejb;

import jakarta.ejb.Remote;

@Remote
public interface HelloTimer {
	public void scheduleTimer(String timerName, int startAfterSec,
			int stopAfterSec);
}
