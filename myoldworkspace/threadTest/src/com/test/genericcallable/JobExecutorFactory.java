package com.test.genericcallable;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class JobExecutorFactory {
	
	

	private JobExecutorFactory(){
		
	}
	
	public static Map<IPackager, FutureTask<List<File>>> getFutureTasksQueue(){
		return  new HashMap<IPackager, FutureTask<List<File>>>();
	}

	public static Map<IPackager, List<File>> executePackingJob(Map<IPackager, FutureTask<List<File>>> futureTasksQueue) throws Exception{
		ExecutorService executor = getFutureTasksExecutor(futureTasksQueue.size());
		consumeFutureTaskQueue(executor, futureTasksQueue);
		Map<IPackager, List<File>> packedMedia = receivePackagedMediaFromFutureTaskQueue(futureTasksQueue);
		shutdownTaksExecutor(executor);		
		return packedMedia;
	}
	
	private static void shutdownTaksExecutor(ExecutorService executor){
		PackagerContext.removeRequestID();
		executor.shutdown();
	}	
	
	
	public static FutureTask<List<File>> makeFutureTask(PackagerTask task){
		FutureTask<List<File>> futuretask = new FutureTask<List<File>>(task);
		return futuretask;
	}
	
	public static Map<IPackager, FutureTask<List<File>>> sendFutureTaskToFutureTaskQueue(FutureTask<List<File>> futureTask , IPackager packager , Map<IPackager, FutureTask<List<File>>> futureTasksQueue){
		futureTasksQueue.put(packager, futureTask);
		return futureTasksQueue;
	}
	
	private static void consumeFutureTaskQueue(ExecutorService executor, Map<IPackager,FutureTask<List<File>>> futureTasksQueue){
		Set<IPackager> packagers = futureTasksQueue.keySet();
		FutureTask<List<File>> currentFutureTask = null;
		for(IPackager packager : packagers){
			currentFutureTask = futureTasksQueue.get(packager);
			executor.execute(currentFutureTask);
		}
		
	}
	
	private static ExecutorService  getFutureTasksExecutor(int fixedThreadPoolSize){
		ExecutorService executor = Executors.newFixedThreadPool(fixedThreadPoolSize);
		return executor;
	}
	
	private static Map<IPackager, List<File>> receivePackagedMediaFromFutureTaskQueue(Map<IPackager,FutureTask<List<File>>> futureTasksQueue) throws Exception{
		Map<IPackager, List<File>> packagedMediaMap = new HashMap<IPackager, List<File>>();
		Set<IPackager> packagers = futureTasksQueue.keySet();
		FutureTask<List<File>> currentTask = null;
		List<File> packagedMedia = null;;
		for(IPackager packager : packagers){
			currentTask = futureTasksQueue.get(packager);
			while (true) {
				if (currentTask.isDone()) {
					packagedMedia = currentTask.get();
					packagedMediaMap.put(packager, packagedMedia);
					break;
				}
			}
		}	
		
		return packagedMediaMap;
	}
	
	
	

	
}
