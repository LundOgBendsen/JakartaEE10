package com.recipes.cdi;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@PerformanceLogging
public class PerformanceLoggingInterceptor {
	@Inject
	Logger log;
	
	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		long start = System.currentTimeMillis();
		Object result = ic.proceed();
		long end = System.currentTimeMillis();
		log.info("PerfLog: Call to " + ic.getTarget().getClass().getSimpleName() + "." + ic.getMethod().getName() + " took " + (end-start) + " ms");
		return result;
	}
}
