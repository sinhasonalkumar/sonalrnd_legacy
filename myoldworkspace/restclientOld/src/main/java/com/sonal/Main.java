package com.sonal;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sonal.webserviceclient.client.WebServiceClient;
import com.sonal.webserviceclient.model.Student;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		WebServiceClient webServiceClient  = (WebServiceClient) applicationContext.getBean("webServiceClient");
		
		String sayHelloGetResponse = webServiceClient.sayHelloGet("sonal");
		System.out.println(sayHelloGetResponse);
		List<Student> allStudents = webServiceClient.getAllStudents();
		System.out.println(allStudents);
		
	}

}
