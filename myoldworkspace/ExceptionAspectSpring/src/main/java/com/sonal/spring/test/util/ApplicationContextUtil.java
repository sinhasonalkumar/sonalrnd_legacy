package com.sonal.spring.test.util;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil{	
	
	private static ApplicationContext ctx = buildApplicationContext();
	
/*	private ApplicationContextUtil(){
		
	}*/
	
	private static ApplicationContext buildApplicationContext(){
		return new ClassPathXmlApplicationContext("application-context.xml");
	}
	
	public static ApplicationContext getApplicationContext(){
		return ApplicationContextUtil.ctx;
	}	

}
