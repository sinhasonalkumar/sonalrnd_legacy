package com.sonal.spring.aspects;



import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.sonal.spring.annotationcontext.SonalsAnnotationContext;
import com.sonal.spring.components.SonalsLogger;

@Aspect
@Component
public class LoggingAspect {

	
	@Around("@annotation(com.sonal.spring.components.SonalsLogger)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	
		System.out.println("Start in SonalsLogger");
		Class clazz = joinPoint.getSignature().getDeclaringType();
		
		Method[] methods = clazz.getMethods();
		for (Method curMethod : methods) {
			Annotation[] annotations = curMethod.getAnnotations();		
			for(Annotation curAnnotation : annotations){
				if (curAnnotation instanceof SonalsLogger) {
					SonalsLogger sonalsLogger = (SonalsLogger) curAnnotation;
					System.out.println(sonalsLogger.level());
					SonalsAnnotationContext.setConfiguration(curMethod.getName(),sonalsLogger.level());
					System.out.println(SonalsAnnotationContext.getAllConfigs().size());
					System.out.println(SonalsAnnotationContext.getAllConfigs());
					
				}
			}
		}
		System.out.println("End in SonalsLogger");
		Object proceed = joinPoint.proceed();
		return proceed;

	}
	
		
}