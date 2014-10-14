package com.sonal.spring.test.test.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceAsync2 {
	
	@Async
	public void packageFiles() throws Exception{
		System.out.println("ServiceAsync2 Start");
		Thread.sleep(1100);
		System.out.println("ServiceAsync2 End");
		
	}

}
