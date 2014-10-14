package com.sonal.spring.poc.fileprocessor.packagers;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public interface IPackager {

	List<File> packageMedia(List<File> sourceFiles);
	
}
