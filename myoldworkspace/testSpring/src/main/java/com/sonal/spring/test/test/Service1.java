package com.sonal.spring.test.test;

import org.springframework.stereotype.Service;

@Service
public class Service1 {
	
	public Service1() {
		System.out.println("Service 1 created");
	}
	
	public void execute(){
		System.out.println("execute Service 1");
	}

}
