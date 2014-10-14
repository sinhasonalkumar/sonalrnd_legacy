package com.test.thread.tasks;

public class GenerateReport implements Task {

	@Override
	public String execute() {
		System.out.println( "Generating Report....");
		return "SUCCEESS";
		
	}

	
}
