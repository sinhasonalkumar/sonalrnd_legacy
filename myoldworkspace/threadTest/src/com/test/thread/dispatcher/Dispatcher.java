package com.test.thread.dispatcher;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.test.thread.tasks.Task;

public class Dispatcher implements Runnable {
	
	private Task task ;
	private static Map<String, Object> taskStatus = new ConcurrentHashMap<String, Object>();
	
	
	
	public Task getTask() {
		return task;
	}


	public void setTask(Task task) {
		this.task = task;
	}


	@Override
	public void run() {		
		Object status = task.execute();
		
	}
	
	
}
