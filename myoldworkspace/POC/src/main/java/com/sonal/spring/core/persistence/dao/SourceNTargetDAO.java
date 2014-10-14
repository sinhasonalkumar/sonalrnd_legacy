package com.sonal.spring.core.persistence.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SourceNTargetDAO implements ISourceNTragetDAO {

	@Override
	public String getSourceFilesGrabber(String sourceType) {
		String fileGrabberClass = null;
		if (sourceType.equalsIgnoreCase("burfiler")) {
			fileGrabberClass = "fileGrabber";
		}
		return fileGrabberClass;
	}

}
