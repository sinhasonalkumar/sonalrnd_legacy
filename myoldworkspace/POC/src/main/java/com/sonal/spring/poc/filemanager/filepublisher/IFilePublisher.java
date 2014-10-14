package com.sonal.spring.poc.filemanager.filepublisher;

import java.io.File;
import java.util.List;

public interface IFilePublisher {
	boolean publishContent(List<File> contents , String targetPublishLocation);
}
