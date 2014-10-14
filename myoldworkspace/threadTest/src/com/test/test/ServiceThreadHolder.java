package com.test.test;

public class ServiceThreadHolder extends Thread {

	private Service1 service1 ;
	
	public ServiceThreadHolder(Service1 service1) {
		this.service1 = service1;
	}
	
	@Override
	public void run() {
		service1.doSomething();
	}
}
