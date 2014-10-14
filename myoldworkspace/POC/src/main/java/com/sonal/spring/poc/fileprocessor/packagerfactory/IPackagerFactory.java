package com.sonal.spring.poc.fileprocessor.packagerfactory;

import com.sonal.spring.poc.fileprocessor.packagers.IPackager;

public interface IPackagerFactory {

	IPackager getPackager(String packagerName);
}
