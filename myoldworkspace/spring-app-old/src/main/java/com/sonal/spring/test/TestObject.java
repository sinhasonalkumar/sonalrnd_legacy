package com.sonal.spring.test;


public class TestObject {

	private String firstName;
	private String lastName;
	
	public TestObject(String firstName,
				String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@InvokeMultiple(numberOfTimesToInvoke=2)
	public void printFirstName(){
		System.out.println(firstName);
	}
	
	@InvokeMultiple(numberOfTimesToInvoke=3)
	public void printLastName(){
		System.out.println(lastName);
	}
	
	@InvokeMultiple
	public void printMessage(){
		System.out.println("printed only once");
	}
		
	
	public void printSecret(){
		System.out.println("this will not be printed");
	}
	
}
