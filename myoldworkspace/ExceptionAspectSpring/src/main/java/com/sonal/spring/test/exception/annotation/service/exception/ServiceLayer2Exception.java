package com.sonal.spring.test.exception.annotation.service.exception;


public class ServiceLayer2Exception extends Throwable {

	public ServiceLayer2Exception(String message) {
		super(message);
	}
	
	public ServiceLayer2Exception(String message, Throwable throwable) {
		super(message,throwable);
	}
	
}
