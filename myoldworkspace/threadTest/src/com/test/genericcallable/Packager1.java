package com.test.genericcallable;

import java.io.File;
import java.util.List;

public class Packager1 implements IPackager{

	@Override
	public List<File> packageSourceFiles(List<File> sourceFiles) {
		System.out.println("Packager1 start");
		try {
			System.out.println(PackagerContext.getRequestID());
			Thread.sleep(5000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("Packager1 end");
		return null;
	}

	
}
