package com.sonal.spring.test.test.exception.annotation.exception;

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

	@Around("@annotation(com.sonal.spring.test.test.exception.annotation.exception.SonalsHandleException)")
	public Object handleExceptions(ProceedingJoinPoint joinPoint) {
		Object returnValue = null;
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();

		Object[] methodArgs = joinPoint.getArgs();

		String transactionId = null;
		try {
			RequestContext requestContext = RequestContextHolder.getRequestContext();
			if (requestContext == null) {
				if (methodArgs.length > 0) {

					for (Object currentArg : methodArgs) {
						if (currentArg instanceof RequestContext) {
							requestContext = (RequestContext) currentArg;
						}
					}
				}
			}
			transactionId = requestContext.getTransactionID();

			System.out.println("START :: Thread ::" + Thread.currentThread().getId() + " TransactionID ::  " + transactionId + " :: " + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");

			if (transactionId == null) {
				System.out.println("New Thread Started :: " + Thread.activeCount());
			}

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
			ExceptionHandlingUtility.diagnoseException(throwable, joinPoint);
		}
		System.out.println("END :: Thread :: " + Thread.currentThread().getId() + " TransactionID ::  " + transactionId + " :: " + signature + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		return returnValue;
	}

}