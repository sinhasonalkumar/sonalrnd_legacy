package com.sonal.spring.test.test;

import org.springframework.stereotype.Service;

@Service
public class Service2 {
	
	public Service2() {
		System.out.println("Service 2 created");
	}

	public void execute(){
		System.out.println("execute Service 2");
	}
}
