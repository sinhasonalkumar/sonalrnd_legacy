package com.sonal.spring.annotation.exception;

import java.util.UUID;

public class RequestLoggerTransactionIDHolder {
	private static final ThreadLocal<String> context = new ThreadLocal<String>();

	public static void startTransaction() {
		String generatedId = UUID.randomUUID().toString();	
		context.set(generatedId);
	}

	public static String getTransactionId() {
		return context.get();
	}

	public static void endTransaction() {		
		context.remove();
	}

}
