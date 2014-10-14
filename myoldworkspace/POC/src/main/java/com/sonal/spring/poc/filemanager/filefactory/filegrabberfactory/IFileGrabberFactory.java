package com.sonal.spring.poc.filemanager.filefactory.filegrabberfactory;

import com.sonal.spring.poc.filemanager.filegrabber.IFileGrabber;

public interface IFileGrabberFactory {

	IFileGrabber getFileGrabber(String sourceType);
}
