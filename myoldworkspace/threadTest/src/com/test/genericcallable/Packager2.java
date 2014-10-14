package com.test.genericcallable;

import java.io.File;
import java.util.List;

public class Packager2 implements IPackager{

	@Override
	public List<File> packageSourceFiles(List<File> sourceFiles) {
		System.out.println("Packager2 start");
		try {
			System.out.println(PackagerContext.getRequestID());
			Thread.sleep(11000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("Packager2 end");
		return null;
	}

	
}
