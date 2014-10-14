package com.sonal.spring.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLocator {

	
	@Autowired
	private Service1 service1;	
	@Autowired
	private Service2 service2;
	
	public Service1 getService1() {
		return service1;
	}
	public Service2 getService2() {
		return service2;
	}
	
	
	
	
}
