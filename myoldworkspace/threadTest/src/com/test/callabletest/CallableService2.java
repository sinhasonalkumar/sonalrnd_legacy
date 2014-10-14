package com.test.callabletest;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableService2 implements Callable<List<File>> {

	@Override
	public List<File> call() throws Exception {
		System.out.println("Executing Service 2");
		Thread.sleep(300);
		System.out.println("Executed Service 2");
		return null;
	}

}
