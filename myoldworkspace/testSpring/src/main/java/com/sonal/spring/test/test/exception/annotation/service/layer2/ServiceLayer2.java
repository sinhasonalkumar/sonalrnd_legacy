package com.sonal.spring.test.test.exception.annotation.service.layer2;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sonal.spring.test.test.exception.annotation.exception.RequestContext;
import com.sonal.spring.test.test.exception.annotation.exception.RequestContextHolder;
import com.sonal.spring.test.test.exception.annotation.exception.SonalsHandleException;
import com.sonal.spring.test.test.exception.annotation.service.layer3.ServiceLayer3;

@Service
public class ServiceLayer2 {
	
	@Autowired
	private ServiceLayer3 serviceLayer3;

	@SonalsHandleException
	@Async
	public void doSomething(RequestContext requestContext) throws IOException, InterruptedException{
		RequestContextHolder.setRequestContext(requestContext); // set the readlocal for the new thread started in threadPool of  ThreadPoolTaskExecutor -- async Spring -- all the async methods are executed are executed in same thread 
		
		System.out.println("Doing something in ServiceLayer2");
		Thread.sleep(4000);
		serviceLayer3.doSomething(requestContext); // doSomething is anyc method, so requestContext is not required to pass as all the async methods are executed are executed in same thread 
		serviceLayer3.sayHello(); //sayHello is anyc method , this shows that requestContext is not required to pass as all the async methods are executed are executed in same thread 
		//System.out.println(0 / 0)
		if(true){
			throw new IOException();	
		}		
		System.out.println("After Exception in ServiceLayer2");
	}
}
