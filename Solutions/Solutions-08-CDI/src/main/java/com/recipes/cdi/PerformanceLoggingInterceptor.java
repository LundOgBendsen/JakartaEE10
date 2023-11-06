package com.recipes.cdi;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@PerformanceLogging
public class PerformanceLoggingInterceptor {
	
	//Injection will cause an infinite loop here
	Logger log = Logger.getLogger(PerformanceLoggingInterceptor.class.getName());
	
	
	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		System.out.println("PERF");
		long start = System.currentTimeMillis();
		Object result = ic.proceed();
		long end = System.currentTimeMillis();
		log.severe("PerfLog: Call to " + ic.getTarget().getClass().getSimpleName() + "." + ic.getMethod().getName() + " took " + (end-start) + " ms");
		return result;
	}
}
