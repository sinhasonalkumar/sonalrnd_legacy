package com.sonal.spring.test;

public class SubTestObject extends TestObject {

	public SubTestObject(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@InvokeMultiple(numberOfTimesToInvoke=5)
	public void printFirstName() {
		super.printFirstName();
	}

}
