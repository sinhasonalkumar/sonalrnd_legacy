package com.test.thread.factory;

import com.test.thread.tasks.Diagnoser;
import com.test.thread.tasks.GenerateReport;
import com.test.thread.tasks.Task;
import com.test.thread.tasks.CleanDBTask;
import com.test.thread.tasks.InvokeDOTLASP;



public class TaskFactory {

	private TaskFactory() {

	}

	public static Task getTask(String taskName) {
		Task task = null;
		if (taskName.equals("CleanDBTask")) {		
			task = new CleanDBTask();
		}

		if (taskName.equals("InvokeDOTLASP")) {
			task = new InvokeDOTLASP();
		}
		
		if (taskName.equals("Diagnoser")) {
			task = new Diagnoser();
		}
		
		if (taskName.equals("GenerateReport")) {
			task = new GenerateReport();
		}
		return task;
	}
}
