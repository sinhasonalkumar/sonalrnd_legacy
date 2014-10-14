package com.sonal.spring.test.exception.annotation.service.facade;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.spring.test.exception.annotation.main.MyRequestVO;
import com.sonal.spring.test.exception.annotation.service.layer1.MyServiceLayer1;

@Service
public class ServiceFacade {

	@Autowired
	private MyServiceLayer1 myServiceLayer1;

	public void doSomething(String name, MyRequestVO requestVO) throws  Throwable {
		//myServiceLayer1.doSomething(name, requestVO);		
		testingPrivateClassLogging();
		System.out.println("After Exception in ServiceFacade");
	}
	
	private void testingPrivateClassLogging(){
		
	}
}
