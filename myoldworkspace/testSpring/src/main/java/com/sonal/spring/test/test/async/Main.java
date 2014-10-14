package com.sonal.spring.test.test.async;

import java.util.concurrent.Future;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sonal.spring.test.util.AppContext;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = AppContext.getInstance().getApplicationContext();
		
		doSomethingVoid();
		Future<String> doSomething = doSomething();
		
		System.out.println("------All Service Calls Made--------");
		
		System.out.println("Checking Async Service Call Status");
		
		System.out.println("------Pinging For Service Call Status--------");
		
		checkStatus(doSomething);
		
		System.out.println("Finished");
		

	}

	
	private static void  checkStatus(Future<String> doSomething) throws Exception {
		boolean isdoSomethingDone = false;
		String result = null;
		while (!isdoSomethingDone) {
			// System.out.println("Check doSometing Status");
			if (doSomething.isDone() && result == null) {				
				result = doSomething.get();
				System.out.println("DoSomething Result : " + result);
				System.out.println("Completed");		
			}
			
			ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor)  AppContext.getInstance().getApplicationContext().getBean("myExecutor");
			
			if(executor.getActiveCount() == 0){
				executor.shutdown();
				isdoSomethingDone = true;
			}
				
		}
	}


	private static Future<String> doSomething() throws Exception {
		System.out.println("Start :: doSomething()");

		ApplicationContext ctx = AppContext.getInstance().getApplicationContext();

		ServiceAsync3 s3 = (ServiceAsync3) ctx.getBean("serviceAsync3");

		Future<String> sayHello = s3.sayHello();
		System.out.println(sayHello);

		System.out.println("End :: doSomething()");
		return sayHello;
	}

	
	private static void doSomethingVoid() throws Exception {
		System.out.println("Start :: doSomethingVoid()");

		ApplicationContext ctx = AppContext.getInstance().getApplicationContext();

		ServiceAsync1 s1 = (ServiceAsync1) ctx.getBean("serviceAsync1");
		ServiceAsync2 s2 = (ServiceAsync2) ctx.getBean("serviceAsync2");

		s1.packageFiles();
		s2.packageFiles();

		System.out.println("End :: doSomethingVoid()");

	}
}
