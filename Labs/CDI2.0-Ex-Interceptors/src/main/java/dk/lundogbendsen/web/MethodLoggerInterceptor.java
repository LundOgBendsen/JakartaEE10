package dk.lundogbendsen.web;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Loggable
public class MethodLoggerInterceptor {

	@AroundInvoke
	public Object auth(InvocationContext ic) throws Exception {
		try {
			System.out.println("Invoking: " + ic.getTarget().getClass() + "::" + ic.getMethod().getName() + "...");
			return ic.proceed();
		} finally {
			System.out.println("Invoking: " + ic.getTarget().getClass() + "::" + ic.getMethod().getName() + "...done !!!!");
		}
	}
}
