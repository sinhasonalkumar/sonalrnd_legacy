package com.sonal.spring.poc.filemanager.filepublisher;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StagePublisher implements IFilePublisher {

	@Override
	public boolean publishContent(List<File> contents, String targetPublishLocation) {
		System.out.println("Published Files to Stager API");		
		return true;
	}

		
}
