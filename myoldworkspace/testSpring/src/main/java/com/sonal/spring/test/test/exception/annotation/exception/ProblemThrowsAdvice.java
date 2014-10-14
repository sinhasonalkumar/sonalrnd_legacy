package com.sonal.spring.test.test.exception.annotation.exception;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProblemThrowsAdvice {

	private static Log logger = LogFactory.getLog(ProblemThrowsAdvice.class);

	@AfterThrowing(pointcut = "execution(* com.sonal..*.*(..)))", throwing = "e")
	public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
		e.printStackTrace();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());
		logger.info("Exception caught exception in method: " + methodName + " with arguments " + arguments + "\nand the full toString: " + stuff + "\nthe exception is: " + e.getMessage(), e);
	}
}
