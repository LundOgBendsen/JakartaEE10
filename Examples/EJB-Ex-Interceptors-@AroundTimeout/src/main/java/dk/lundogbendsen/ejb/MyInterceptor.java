package dk.lundogbendsen.ejb;

import jakarta.ejb.Timer;
import jakarta.interceptor.AroundTimeout;
import jakarta.interceptor.InvocationContext;

public class MyInterceptor {

	@AroundTimeout
	Object aroundTimeout(InvocationContext ctx) throws Exception {
		System.out.println("MyInterceptor.aroundTimeout()");

		Timer timer = (Timer) ctx.getTimer();
		String info = timer.getInfo().toString();
		System.out.println("\tTimer.info: " + info);
		
		return ctx.proceed();
	}

}
