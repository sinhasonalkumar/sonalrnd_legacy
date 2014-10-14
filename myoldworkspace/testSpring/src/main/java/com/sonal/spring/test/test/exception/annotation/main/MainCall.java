package com.sonal.spring.test.test.exception.annotation.main;



import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.sonal.spring.test.test.exception.annotation.service.layer1.ServiceLayer1;


public class MainCall {
	
	public static void main(String[] args) throws Throwable {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		ServiceLayer1 serviceLayer1 = (ServiceLayer1) ctx.getBean("serviceLayer1");		
		RequestVO requestVO = new RequestVO();
		requestVO.setId("123i3eir9");
		requestVO.setName("ABC");		
		serviceLayer1.doSomething(null, requestVO);
		System.out.println("end");
		
	}
}
