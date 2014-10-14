package com.test.genericcallable;

import java.io.File;
import java.util.List;

public class Packager3 implements IPackager{

	@Override
	public List<File> packageSourceFiles(List<File> sourceFiles) {
		System.out.println("Packager3 start");
		try {
			System.out.println(PackagerContext.getRequestID());
			Thread.sleep(8000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("Packager3 end");
		return null;
	}

	
}
