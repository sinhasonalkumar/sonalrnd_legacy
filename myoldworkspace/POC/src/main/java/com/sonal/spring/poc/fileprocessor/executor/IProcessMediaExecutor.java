package com.sonal.spring.poc.fileprocessor.executor;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.sonal.spring.poc.fileprocessor.packagers.IPackager;

public interface IProcessMediaExecutor {

	Map<IPackager,List<File>> executeProcessing(List<File> sourceFiles, List<IPackager> packagers);
}
