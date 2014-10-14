package com.sonal.spring.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sonal.spring.jms.producer.MessageProducer;

public class TestApp {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		MessageProducer messageProducer = (MessageProducer) ctx.getBean("messageProducer");
		for(int i = 0 ;i<=10;i++){
			messageProducer.send("Message : " + i);
		}
	}
}
