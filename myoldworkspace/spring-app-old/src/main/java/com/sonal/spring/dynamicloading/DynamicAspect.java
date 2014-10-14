package com.sonal.spring.dynamicloading;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicAspect {

	@Autowired
	private ConfigVO configVO;
	
	@Around("@annotation(com.sonal.spring.dynamicloading.SonalsDynamicProLoader)")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	
		System.out.println("In SonalsDynamicProLoader " + configVO.getDetailLoggingAspect());		
		joinPoint.proceed();

	}
	
		
}