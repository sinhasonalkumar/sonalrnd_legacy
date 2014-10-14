package com.sonal.spring.poc.fileprocessor.packagers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class WidevinePackager implements IPackager{	

	@Override
	public List<File> packageMedia(List<File> sourceFiles) {		
		List<File> packagedFiles = new ArrayList<File>();
		System.out.println("Widevine Packaging Start");
		System.out.println("Widevine Packaging End");
		return packagedFiles;
	}		
	
}
