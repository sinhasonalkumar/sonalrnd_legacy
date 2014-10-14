package com.test.callabletest;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableService1 implements Callable<List<File>> {

	@Override
	public List<File> call() throws Exception {
		System.out.println("Executing Service 1");
		Thread.sleep(10000);
		System.out.println("Executed Service 1");
		return null;
	}
	
	
	

}
