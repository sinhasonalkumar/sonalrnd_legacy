package com.sonal.spring.test.exception.annotation.service.layer3;



import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class MyServiceLayer3 {

	public void doSomething() throws Throwable{
		System.out.println("Doing something in ServiceLayer3");
		//Thread.sleep(4000);
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer3");
	}

	public void sayHello() throws Throwable{
		System.out.println("Doing sayHello in ServiceLayer3");
		//Thread.sleep(4000);
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer3");
	}
}
