package com.sonal.spring.test.test.async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class ServiceAsync3 {

	@Async
	public Future<String> sayHello() throws Exception{
		System.out.println("ServiceAsync3 Start");
		Thread.sleep(800);
		System.out.println("ServiceAsync3 End");
		String result = "Hello Async !!";
		
		return new AsyncResult<String>(result);
	}
}
