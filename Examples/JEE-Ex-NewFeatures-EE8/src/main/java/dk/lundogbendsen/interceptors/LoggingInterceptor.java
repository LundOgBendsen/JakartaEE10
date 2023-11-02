package dk.lundogbendsen.interceptors;

import java.lang.reflect.Method;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import dk.lundogbendsen.annotations.LoggedCall;

@LoggedCall
@Interceptor
public class LoggingInterceptor {

	@AroundInvoke
	public Object performLoggin(InvocationContext ic) throws Exception {
		Method method = ic.getMethod();

		System.out.println("Calling "
				+ method.getDeclaringClass().getSimpleName() + "."
				+ method.getName() + "(...)");
		try {
			Object proceed = ic.proceed();
			return proceed;
		} catch (Exception e) {
			System.out.println("Exception occurred in "
					+ method.getClass().getSimpleName() + "."
					+ method.getName() + "(...)");
			throw e;
		}
	}
}