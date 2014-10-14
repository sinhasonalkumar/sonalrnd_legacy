package com.sonal.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.sonal.spring.services.CustomerService;

public class MainProg {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImpl");
		customerService.addCustomerAround("sonal");

	}

}
