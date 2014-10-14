package com.sonal.spring.test.exception.annotation.service.layer1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.spring.test.exception.annotation.main.MyRequestVO;
import com.sonal.spring.test.exception.annotation.service.layer2.MyServiceLayer2;

@Service
public class MyServiceLayer1 {

	@Autowired
	private MyServiceLayer2 myServiceLayer2;

	public void doSomething(String name, MyRequestVO requestVO) throws  Throwable {
		myServiceLayer2.doSomething();
		System.out.println(name.toString());
		System.out.println("After Exception in ServiceLayer1");
	}
}
