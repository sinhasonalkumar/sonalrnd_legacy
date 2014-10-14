package com.sonal.spring.poc.filemanager.filefactory.filepublisherfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sonal.spring.poc.filemanager.filepublisher.IFilePublisher;
import com.sonal.spring.poc.util.ApplicationContextUtil;

@Component
public class FilePublisherFactory implements IFilePublisherFactory {

	@Override
	public IFilePublisher getFilePublisher(String publisherName) {
		ApplicationContext ctx = ApplicationContextUtil.getInstance().getApplicationContext();
		IFilePublisher publisher =  (IFilePublisher) ctx.getBean(publisherName);
		return publisher;
		
	}
	
		
}
