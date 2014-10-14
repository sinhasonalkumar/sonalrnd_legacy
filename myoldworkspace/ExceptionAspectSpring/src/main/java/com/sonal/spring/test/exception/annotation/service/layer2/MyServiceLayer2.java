package com.sonal.spring.test.exception.annotation.service.layer2;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.spring.test.exception.annotation.service.layer3.MyServiceLayer3;

@Service
public class MyServiceLayer2 {
	
	@Autowired
	private MyServiceLayer3 myServiceLayer3;

	public void doSomething() throws Throwable{
		
		System.out.println("Doing something in ServiceLayer2");
		//Thread.sleep(4000);
		myServiceLayer3.doSomething();
		//myServiceLayer3.sayHello(); 
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer2");
	}
}
