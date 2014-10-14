package com.sonal.spring.poc.filemanager.filefactory.filepublisherfactory;

import com.sonal.spring.poc.filemanager.filepublisher.IFilePublisher;

public interface IFilePublisherFactory {
	IFilePublisher getFilePublisher(String publisherName);
}
