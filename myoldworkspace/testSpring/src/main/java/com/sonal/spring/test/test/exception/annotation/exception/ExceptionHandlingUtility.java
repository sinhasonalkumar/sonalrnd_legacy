package com.sonal.spring.test.test.exception.annotation.exception;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class ExceptionHandlingUtility {

	
	public static void diagnoseException(Throwable throwable, JoinPoint joinPoint){

		
		Signature signature = joinPoint.getSignature();
		String methodName = joinPoint.getSignature().getName();			
		String className = joinPoint.getTarget().getClass().getName();
		
		Object[] methodArgs = joinPoint.getArgs();
		
		String transactionId = null;
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
		
		
		String messgage = null;
		
		StackTraceElement[] stackTrace = throwable.getStackTrace();
		String currentLine = null;
		for(StackTraceElement currentStackTraceElement : stackTrace){
			currentLine = currentStackTraceElement.toString();
			if(currentLine.contains(className+"."+methodName)){
				System.err.println( "START :: Thread :: " + Thread.currentThread().getId() + " TransactionID ::  " + transactionId + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
				messgage = "Exception caught in " + signature + " method at Line number : " + currentStackTraceElement.getLineNumber() + " and with error Messagage as : " + throwable.toString();
			    System.err.println(currentLine);
			
			}
			
		}
		
		System.err.println(messgage);
		if(methodArgs.length > 0){
			System.err.println("Method Arguments :-");
			int argNo = 1;
			for(Object currentArg : methodArgs){
				if(currentArg !=null){
					System.err.println("Arg"+argNo + " : " + currentArg.getClass().getSimpleName() + " = "+currentArg.toString());
				}else{
					System.err.println("Arg"+argNo + " : is " + currentArg);
				}
				argNo++;
			}
		}else{
			System.err.println("No Method Arguments");
		}
		System.err.println("END  :: Thread ::" + Thread.currentThread().getId() + " TransactionID ::  " +transactionId + " :: ------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	
	}
	
}
