package com.test.genericcallable;



import java.io.File;
import java.util.List;

public class PackageNPublishTaskImpl implements PackagerTask {

	private List<File> sourceFiles;
	private IPackager packager;
	private String requestID;
	
	@Override
	public List<File> call() throws Exception {
		System.out.println("Start PackageNPublishJob ");
		PackagerContext.setRequestID(requestID);		
		packageNPublish(sourceFiles, packager);		
		System.out.println("End PackageNPublishJob ");
		PackagerContext.removeRequestID();
		return null;
	}

	@Override
	public boolean packageNPublish(List<File> sourceFiles, IPackager packager) {		
		packager.packageSourceFiles(sourceFiles);		
		return true;
	}

	public List<File> getSourceFiles() {
		return sourceFiles;
	}

	public void setSourceFiles(List<File> sourceFiles) {
		this.sourceFiles = sourceFiles;
	}

	public IPackager getPackager() {
		return packager;
	}

	public void setPackager(IPackager packager) {
		this.packager = packager;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}	
	
	
	

}
