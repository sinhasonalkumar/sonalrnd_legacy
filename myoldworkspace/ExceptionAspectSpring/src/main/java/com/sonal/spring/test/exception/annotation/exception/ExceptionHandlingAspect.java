package com.sonal.spring.test.exception.annotation.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sonal.spring.test.exception.annotation.service.exception.ServiceLayer1Exception;
import com.sonal.spring.test.exception.annotation.service.exception.ServiceLayer2Exception;
import com.sonal.spring.test.exception.annotation.service.exception.ServiceLayer3Exception;

@Order(value = 1)
@Aspect
@Component
public class ExceptionHandlingAspect {

	@Pointcut("execution(* com.sonal.spring.test.exception.annotation.service.layer1..*.*(..)))")
	public void serviceLayer1Methods() {
		//
	}
	
	@Pointcut("execution(* com.sonal.spring.test.exception.annotation.service.facade..*.*(..)))")
	public void facadeLayerMethods() {
		//
	}

	@Around("serviceLayer1Methods()")
	public Object handleServiceLayer1Exceptions(ProceedingJoinPoint joinPoint) throws ServiceLayer1Exception {
		Object returnValue = null;
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();

		Object[] methodArgs = joinPoint.getArgs();

		System.out.println("Start :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");

		try {
			returnValue = joinPoint.proceed();
		} catch (Throwable e) {
			System.out.println("Exception Caught in ServiceLayer1");
			System.out.println(e.getClass());
			e.printStackTrace();
			throw new ServiceLayer1Exception(e.getCause() + "::" + e.getMessage());
		}

		System.out.println("END :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return returnValue;
	}

	@Around("execution(* com.sonal.spring.test.exception.annotation.service.layer2..*.*(..)))")
	public Object handleServiceLayer2Exceptions(ProceedingJoinPoint joinPoint) throws ServiceLayer2Exception {
		Object returnValue = null;
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();

		Object[] methodArgs = joinPoint.getArgs();
		System.out.println("Start :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		try {
			returnValue = joinPoint.proceed();
		} catch (Throwable e) {
			System.out.println("Exception Caught in ServiceLayer2");
			System.out.println(e.getClass());
			e.printStackTrace();
			throw new ServiceLayer2Exception(e.getCause() + "::" + e.getMessage());
		}

		System.out.println("END :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return returnValue;
	}

	@Around("execution(* com.sonal.spring.test.exception.annotation.service.layer3..*.*(..)))")
	public Object handleServiceLayer3Exceptions(ProceedingJoinPoint joinPoint) throws ServiceLayer3Exception {
		Object returnValue = null;
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();

		Object[] methodArgs = joinPoint.getArgs();
		System.out.println("Start :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		try {
			returnValue = joinPoint.proceed();
		} catch (Throwable e) {
			System.out.println("Exception Caught in ServiceLayer3");
			System.out.println(e.getClass());
			e.printStackTrace();
			throw new ServiceLayer3Exception(e.getCause() + "::" + e.getMessage());
		}

		System.out.println("END :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return returnValue;
	}

	@Around("facadeLayerMethods()")
	public Object handleFacadeExceptions(ProceedingJoinPoint joinPoint) {
		Object returnValue = null;
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();

		Object[] methodArgs = joinPoint.getArgs();
		System.out.println("Start :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		try {
			returnValue = joinPoint.proceed();
		} catch (ServiceLayer1Exception e) {

		} catch (ServiceLayer2Exception e) {
			e.printStackTrace();

		} catch (ServiceLayer3Exception e) {
			e.printStackTrace();

		} catch (Throwable e) {
			System.out.println("Unknown Exception");
			e.printStackTrace();

		}

		System.out.println("END :: Thread :: " + Thread.currentThread().getId() + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return returnValue;
	}

}