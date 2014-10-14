package com.test.thread.tasks;

public class CleanDBTask implements Task {

	@Override
	public String execute() {
		System.out.println( "Clean DB Task is running....");
		return "DB Cleaned";
	}

	
}
