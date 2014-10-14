package com.sonal.spring.test.test.exception.annotation.service.layer1;





import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.spring.test.test.exception.annotation.exception.RequestContextHolder;
import com.sonal.spring.test.test.exception.annotation.exception.SonalsHandleException;
import com.sonal.spring.test.test.exception.annotation.exception.SonalsLoggerAnnotation;
import com.sonal.spring.test.test.exception.annotation.main.RequestVO;
import com.sonal.spring.test.test.exception.annotation.service.layer2.ServiceLayer2;

@Service
public class ServiceLayer1 {

	@Autowired
	private ServiceLayer2 serviceLayer2;
	
	@SonalsLoggerAnnotation
	@SonalsHandleException	
	public void doSomething(String name, RequestVO requestVO) throws IOException, InterruptedException{			
		serviceLayer2.doSomething(RequestContextHolder.getRequestContext()); // new Thread will start in the threadPool of ThreadPoolTaskExector of Async Spring		
		System.out.println(name.toString());
		System.out.println("After Exception in ServiceLayer1");
	}
}
