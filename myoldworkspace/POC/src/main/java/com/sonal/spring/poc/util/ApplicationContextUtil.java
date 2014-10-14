package com.sonal.spring.poc.util;



import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil{	
	
	private static final ApplicationContextUtil appContext = init();
	private ApplicationContext springApplicationContext ;
	
	private ApplicationContextUtil(){
		
	}
	
	private static ApplicationContextUtil init(){
		ApplicationContextUtil context = new ApplicationContextUtil();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		context.springApplicationContext = applicationContext;
		return context;
	}
	
	public static ApplicationContextUtil getInstance(){
		return appContext;
	}
	
	public ApplicationContext getApplicationContext(){
		return appContext.springApplicationContext;
	}

}
