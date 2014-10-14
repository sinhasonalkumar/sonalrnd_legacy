package com.sonal.spring.test.exception.annotation.service.exception;


public class ServiceLayer1Exception extends Throwable {

	public ServiceLayer1Exception(String message) {
		super(message);
	}
	
	public ServiceLayer1Exception(String message, Throwable throwable) {
		super(message,throwable);
	}
	
}
