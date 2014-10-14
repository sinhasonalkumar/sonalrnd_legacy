package com.sonal.spring.dynamicloading;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class MainDynamicLoading {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		
		TestDynamicPropClass testClass = ctx.getBean(TestDynamicPropClass.class);		
		testClass.doSomething();
		Thread.sleep(40000);		
		testClass.doSomething();
		
	 
	}
}
