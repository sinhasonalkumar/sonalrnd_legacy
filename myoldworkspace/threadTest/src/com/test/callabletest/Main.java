package com.test.callabletest;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class Main {

	public static void main(String[] args) {
		futureExample();
	}
	
	private static void futureExample(){

		CallableService1 service1 = new CallableService1();
		CallableService2 service2 = new CallableService2();
		FutureTask<List<File>> futureService1 = new FutureTask<List<File>>(service1); 
		FutureTask<List<File>> futureService2 = new FutureTask<List<File>>(service2);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureService1);
		executor.execute(futureService2);

		while (true) {			
			if(futureService1.isDone() && futureService2.isDone()){
				executor.shutdown();
				System.out.println("Both Service are Done Service1 and Service2");
				return;
			}
			if(futureService1.isDone()){
				System.out.println("Service1 is done...Wating for service 2");
			}
			if(futureService2.isDone()){
				System.out.println("Service2 is done...Wating for service 1");
			}
		}

	
	}

}
