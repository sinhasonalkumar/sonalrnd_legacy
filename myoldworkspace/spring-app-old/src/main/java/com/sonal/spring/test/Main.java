package com.sonal.spring.test;



public class Main {

	public static void main(String[] args) {
		TestObject theObject = new TestObject("Homer","Simpson");
		MultipleInvoker.invokeThis(theObject);
		
		System.out.println("_________________________________");
	
		MultipleInvoker.invokeThis(theObject);
	}

}
