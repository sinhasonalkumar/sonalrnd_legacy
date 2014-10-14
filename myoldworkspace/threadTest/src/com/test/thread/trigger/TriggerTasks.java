package com.test.thread.trigger;



import java.util.ArrayList;
import java.util.List;

import com.test.thread.factory.DispatcherFactory;


public class TriggerTasks {
	public static void main(String[] args) {
		
		List<String> tasksToExecute = intTasksToExecute();
		
		for(String currentTask : tasksToExecute){
			DispatcherFactory.getTaskDispatcher(currentTask).run();
		}
		
	}
	
	private static List<String> intTasksToExecute(){
		List<String> tasks  = new ArrayList<String>();
		tasks.add("CleanDBTask");
		tasks.add("InvokeDOTLASP");
		tasks.add("Diagnoser");
		tasks.add("GenerateReport");
		return tasks;
	}

}
