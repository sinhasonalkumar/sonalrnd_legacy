package com.sonal.spring.facade.locator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sonal.spring.poc.configurationstore.configurationfactory.IConfigurationFactory;
import com.sonal.spring.poc.filemanager.filefactory.filegrabberfactory.IFileGrabberFactory;
import com.sonal.spring.poc.filemanager.filefactory.filepublisherfactory.IFilePublisherFactory;
import com.sonal.spring.poc.fileprocessor.packagerfactory.IPackagerFactory;

@Component
public class FactoryLocator {
	@Autowired
	private IConfigurationFactory configurationFactory;

	@Autowired
	private IFileGrabberFactory fileGrabberFactory;

	@Autowired
	private IPackagerFactory packagerFactory;

	@Autowired
	private IFilePublisherFactory filePublisherFactory;

	public IConfigurationFactory getConfigurationFactory() {
		return configurationFactory;
	}

	public IFileGrabberFactory getFileGrabberFactory() {
		return fileGrabberFactory;
	}

	public IPackagerFactory getPackagerFactory() {
		return packagerFactory;
	}

	public IFilePublisherFactory getFilePublisherFactory() {
		return filePublisherFactory;
	}
	
	
	
}
