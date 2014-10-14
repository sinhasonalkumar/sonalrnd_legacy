package com.sonal.spring.test.exception.annotation.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sonal.spring.test.exception.annotation.service.facade.ServiceFacade;


public class MainCall {
	
	public static void main(String[] args) throws Throwable {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		ServiceFacade serviceFacade = (ServiceFacade) ctx.getBean(ServiceFacade.class);		
		MyRequestVO requestVO = new MyRequestVO();
		requestVO.setId("123i3eir9");
		requestVO.setName("ABC");		
		serviceFacade.doSomething(null, requestVO);
		System.out.println("end");
		
	}
}
