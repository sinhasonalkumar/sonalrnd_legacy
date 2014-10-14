package com.sonal.spring.annotation.service.exception;

public class Layer2Exception extends Throwable {

	public Layer2Exception(String message) {
		super(message);
	}
	
	public Layer2Exception(String message, Throwable throwable) throws MyException {
		super(message,throwable);
	}
	
}
