package com.test.genericcallable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

public class Main {

	public static void main(String[] args) throws Exception{
		List<File> sourceFiles = getSourceFiles();
		List<IPackager> packagers = getPackagers();		
		PackagerContext.setRequestID("ThreadLocalRequest1");
		Map<IPackager, FutureTask<List<File>>> futureTasksQueue = JobExecutorFactory.getFutureTasksQueue();
		
		for (IPackager packager : packagers) {
			PackagerTask task = getPackagerTask(sourceFiles, packager);
			FutureTask<List<File>> futureTask = JobExecutorFactory.makeFutureTask(task);			
			futureTasksQueue = JobExecutorFactory.sendFutureTaskToFutureTaskQueue(futureTask, packager,futureTasksQueue);
		}
		Map<IPackager, List<File>> packagedMedia = JobExecutorFactory.executePackingJob(futureTasksQueue);
		System.out.println(packagedMedia);
	}
	
	
	
	private static PackagerTask getPackagerTask(List<File> sourceFiles,IPackager packager){
		PackagerTask task = new PackageNPublishTaskImpl();
		task.setSourceFiles(sourceFiles);
		task.setPackager(packager);
		task.setRequestID(PackagerContext.getRequestID());
		return task;
	}

	
	private static List<File> getSourceFiles() {
		return new ArrayList<File>();
	}

	private static List<IPackager> getPackagers() {
		IPackager packager1 = new Packager1();
		IPackager packager2 = new Packager2();
		IPackager packager3 = new Packager3();

		List<IPackager> packagers = new ArrayList<IPackager>();
		packagers.add(packager1);
		packagers.add(packager2);
		packagers.add(packager3);
		return packagers;
	}

	
}
