package com.test.threadlocal;

public class TransactionManager {
	private static final ThreadLocal<String> context = new ThreadLocal<String>();

	public static void startTransaction() {
		// logic to start a transaction
		// ...
		//context.set(generatedId);
	}

	public static String getTransactionId() {
		return context.get();
	}

	public static void endTransaction() {
		// logic to end a transaction
		// …
		context.remove();
	}
}
