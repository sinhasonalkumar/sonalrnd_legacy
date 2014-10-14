package com.test.thread.tasks;

public class InvokeDOTLASP implements Task {

	@Override
	public String execute() {
		System.out.println( "Invoke DOT LASP ....");
		return "DOT LASP Invoked";
		
	}

	
}
