package com.sonal.spring.test.test.exception.annotation.service.layer3;




import java.io.IOException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sonal.spring.test.test.exception.annotation.exception.RequestContext;
import com.sonal.spring.test.test.exception.annotation.exception.SonalsHandleException;

@Service
public class ServiceLayer3 {

	@SonalsHandleException
	@Async
	public void doSomething(RequestContext requestContext) throws IOException, InterruptedException{
		System.out.println("Doing something in ServiceLayer3");
		Thread.sleep(4000);
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer3");
	}
	
	@SonalsHandleException
	@Async
	public void sayHello() throws IOException, InterruptedException{
		System.out.println("Doing sayHello in ServiceLayer3");
		Thread.sleep(4000);
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer3");
	}
}
