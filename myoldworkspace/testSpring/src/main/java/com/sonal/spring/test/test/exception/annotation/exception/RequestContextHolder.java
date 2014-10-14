package com.sonal.spring.test.test.exception.annotation.exception;

import java.util.UUID;

public class RequestContextHolder {
	private static final ThreadLocal<RequestContext> context = new ThreadLocal<RequestContext>();

	public static void startTransaction() {
		String generatedId = UUID.randomUUID().toString();	
		RequestContext requestContext = new RequestContext();
		requestContext.setTransactionID(generatedId);
		context.set(requestContext);
	}
	
	public static void setRequestContext(RequestContext requestContext) {
		context.set(requestContext);
	}

	public static RequestContext getRequestContext() {
		return context.get();
	}

	public static void endTransaction() {		
		context.remove();
	}

}
