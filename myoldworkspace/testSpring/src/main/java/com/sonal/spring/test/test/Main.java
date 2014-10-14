package com.sonal.spring.test.test;

import org.springframework.context.ApplicationContext;

import com.sonal.spring.test.util.AppContext;

public class Main {
	public static void main(String[] args) {		
		AppContext appContext = AppContext.getInstance();
		ApplicationContext ctx = appContext.getApplicationContext();
		
		ServiceLocator serviceLocator = (ServiceLocator) ctx.getBean("serviceLocator");
		
		Service1 service1 = serviceLocator.getService1();
		Service2 service2 = serviceLocator.getService2();
		
		service1.execute();
		service2.execute();
	}

}
