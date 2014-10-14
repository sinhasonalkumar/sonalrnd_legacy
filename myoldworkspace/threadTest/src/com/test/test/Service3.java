package com.test.test;

public class Service3 implements Runnable {
	
	
	@Override
	public void run() {
		doSomething();
	}

	public void doSomething() {
		System.out.println("Doing something in Service3");
		try {
			Thread.sleep(3000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
