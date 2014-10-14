package com.test.test;

public class Service1 {

	public void doSomething(){
		System.out.println("Doing something in Service1");
		try {
			Thread.sleep(3000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
