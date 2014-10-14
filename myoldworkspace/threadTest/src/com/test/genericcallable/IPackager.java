package com.test.genericcallable;

import java.io.File;
import java.util.List;

public interface IPackager {

	List<File> packageSourceFiles(List<File> sourceFiles); 
}
