package com.test.genericcallable;

public class PackagerContext {
	private static final ThreadLocal<String> context = new ThreadLocal<String>();

	public static void setRequestID(String requestID) {

		context.set(requestID);
	}

	public static String getRequestID() {
		return context.get();
	}

	public static void removeRequestID() {
		context.remove();
	}
}
