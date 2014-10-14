package com.sonal.spring.test.util;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class AppContext {
	
	private static final AppContext appContext = init();
	private ApplicationContext springApplicationContext ;
	
	private AppContext(){
		
	}
	
	private static AppContext init(){
		AppContext context = new AppContext();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		context.springApplicationContext = applicationContext;
		return context;
	}
	
	public static AppContext getInstance(){
		return appContext;
	}
	
	public ApplicationContext getApplicationContext(){
		return appContext.springApplicationContext;
	}

}
