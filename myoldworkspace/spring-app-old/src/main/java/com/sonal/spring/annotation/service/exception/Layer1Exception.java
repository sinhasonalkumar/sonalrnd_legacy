package com.sonal.spring.annotation.service.exception;

public class Layer1Exception extends Throwable {

	public Layer1Exception(String message) {
		super(message);
	}
	
	public Layer1Exception(String message, Throwable throwable) {
		super(message,throwable);
	}
	
}
