package com.test.genericcallable;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public interface PackagerTask extends Callable<List<File>>{	

	boolean packageNPublish(List<File> sourceFiles, IPackager packager);
	void setSourceFiles(List<File> sourceFiles);
	void setPackager(IPackager packager);
	void setRequestID(String requestID);
	
}
