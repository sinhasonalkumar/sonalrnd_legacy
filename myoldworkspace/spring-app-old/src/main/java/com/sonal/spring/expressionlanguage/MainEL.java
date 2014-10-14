package com.sonal.spring.expressionlanguage;

import java.util.Map;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class MainEL {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		TestEL testEL = (TestEL) ctx.getBean("testEL");
		CacheClass cacheClass = (CacheClass) ctx.getBean("cacheClass");
		
		System.out.println(testEL.getTest());
		
		
		ctx.refresh();
		
		Map<String, String> config = cacheClass.getConfig();
		config.clear();
		config.put("test", "impl1");
		cacheClass.setConfig(config);
		
		
		System.out.println(testEL.getTest());
	}
}
