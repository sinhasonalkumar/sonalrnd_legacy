package com.sonal;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.sonal.services.Service1;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		Service1 service1 = (Service1)  ctx.getBean("service1");
		service1.showAllConfigs();
	}
}
