package com.sonal.spring.poc.filemanager.filefactory.filegrabberfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sonal.spring.core.persistence.dao.ISourceNTragetDAO;
import com.sonal.spring.poc.filemanager.filegrabber.FileGrabber;
import com.sonal.spring.poc.filemanager.filegrabber.IFileGrabber;
import com.sonal.spring.poc.util.ApplicationContextUtil;

@Component
public class FileGrabberFactory implements IFileGrabberFactory {
	
	@Autowired
	private ISourceNTragetDAO sourceNTargetDAO;

	@Override
	public IFileGrabber getFileGrabber(String sourceType) {
		String fileGrabberClass = sourceNTargetDAO.getSourceFilesGrabber(sourceType);
		IFileGrabber fileGrabber = (FileGrabber)ApplicationContextUtil.getInstance().getApplicationContext().getBean(fileGrabberClass);
		
		return fileGrabber;
	}

	
}
