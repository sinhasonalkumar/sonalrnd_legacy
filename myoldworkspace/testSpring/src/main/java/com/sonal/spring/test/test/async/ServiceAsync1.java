package com.sonal.spring.test.test.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceAsync1 {

	@Async
	public void packageFiles() throws Exception{
		System.out.println("ServiceAsync1 Start");
		Thread.sleep(10000);
		System.out.println("ServiceAsync1 End");
		
	}
}
