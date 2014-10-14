package com.sonal.spring.annotation.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Aspect
@Component
public class LoggerAspect {

	@Around("@annotation(com.sonal.spring.annotation.exception.SonalsLoggerAnnotation)")
	public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable{
		RequestLoggerTransactionIDHolder.startTransaction();
		Object proceed = joinPoint.proceed();
		RequestLoggerTransactionIDHolder.endTransaction();
		return proceed;

	}

}