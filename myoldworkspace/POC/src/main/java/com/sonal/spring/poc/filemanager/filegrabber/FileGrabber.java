package com.sonal.spring.poc.filemanager.filegrabber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FileGrabber implements IFileGrabber{

	@Override
	public List<File> getFiles(String fileLocation) {
		List<File> sourceFiles = new ArrayList<File>();		
		return sourceFiles;
	}

	
}
