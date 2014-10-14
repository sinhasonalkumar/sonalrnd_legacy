package com.test.thread.tasks;

public class Diagnoser implements Task {

	@Override
	public String execute() {
		System.out.println( "Diagnosing DOT LASP ....");
		return "All Good";
	}

	
}
