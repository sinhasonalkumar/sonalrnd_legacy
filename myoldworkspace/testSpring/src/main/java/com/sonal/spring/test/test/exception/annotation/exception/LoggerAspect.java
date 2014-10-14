package com.sonal.spring.test.test.exception.annotation.exception;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Aspect
@Component
public class LoggerAspect {
	
	@Autowired
	private ThreadPoolTaskExecutor myExecutor;

	@Around("@annotation(com.sonal.spring.test.test.exception.annotation.exception.SonalsLoggerAnnotation)")
	public void handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable{
		RequestContextHolder.startTransaction();
		joinPoint.proceed();
		RequestContextHolder.endTransaction();

	}
	
	@Around("@annotation(org.springframework.scheduling.annotation.Async)")
	public void cascadeLoggerTransactionID(ProceedingJoinPoint joinPoint) throws Throwable{
			
		System.out.println(RequestContextHolder.getRequestContext() + " sonal Kumar Sinha");
		
		joinPoint.proceed();
	}

}