package com.sonal.spring.poc.filemanager.filegrabber;

import java.io.File;
import java.util.List;

public interface IFileGrabber {
	
	List<File> getFiles(String fileLocation);
}
