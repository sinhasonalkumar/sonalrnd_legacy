package com.test.test;

public class Main {

	public static void main(String[] args) {
		execute();
	}

	private static void execute() {
		System.out.println("Start");
		Service1 service1 = new Service1();
		Service2 service2 = new Service2();

		ServiceThreadHolder serviceThreadHolder = new ServiceThreadHolder(service1);
		serviceThreadHolder.start();
		service2.doSomething();
		

		Service3 service3 = new Service3();
		Thread thread = new Thread(service3);
		thread.start();
		
		System.out.println("End");
	}
}
