package com.sonal.spring.annotation.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Aspect
@Component
public class HandleExceptionAspect {

	@Around("@annotation(com.sonal.spring.annotation.exception.SonalsHandleException)")
	public Object handleExceptions(ProceedingJoinPoint joinPoint) {
		Object returnValue = null;
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();

		Object[] methodArgs = joinPoint.getArgs();
		try {
			System.out.println("START :: " + RequestLoggerTransactionIDHolder.getTransactionId() + " :: " + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");

			if (methodArgs.length > 0) {
				System.out.println("Method Arguments :-");
				int argNo = 1;
				for (Object currentArg : methodArgs) {
					if (currentArg != null) {
						System.out.println("Arg" + argNo + " : " + currentArg.getClass().getSimpleName() + " = " + currentArg.toString());
					} else {
						System.out.println("Arg" + argNo + " : is " + currentArg);
					}
					argNo++;
				}
			} else {
				System.out.println("No Method Arguments");
			}

			returnValue = joinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			ExceptionHandlingUtility.diagnoseException(throwable, joinPoint);
		}
		System.out.println("END :: " + RequestLoggerTransactionIDHolder.getTransactionId() + " :: " + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return returnValue;
	}

}