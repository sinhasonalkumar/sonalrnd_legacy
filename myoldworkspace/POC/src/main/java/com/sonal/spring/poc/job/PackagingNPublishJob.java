package com.sonal.spring.poc.job;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

@Component
public class PackagingNPublishJob implements Callable<List<File>>{

	@Override
	public List<File> call() throws Exception {
		
		
		return null;
	}

	
}
