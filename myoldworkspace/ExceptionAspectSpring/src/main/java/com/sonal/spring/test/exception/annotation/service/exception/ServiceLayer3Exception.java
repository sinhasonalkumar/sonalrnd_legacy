package com.sonal.spring.test.exception.annotation.service.exception;


public class ServiceLayer3Exception extends Throwable {

	public ServiceLayer3Exception(String message) {
		super(message);
	}
	
	public ServiceLayer3Exception(String message, Throwable throwable) {
		super(message,throwable);
	}
	
}
