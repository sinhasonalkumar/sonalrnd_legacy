package com.test.thread.factory;


import com.test.thread.dispatcher.Dispatcher;

public class DispatcherFactory {
	
	private DispatcherFactory(){
		
	}
	
	public static Dispatcher getTaskDispatcher(String taskName){
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setTask(TaskFactory.getTask(taskName));
		return dispatcher;
	}

}
