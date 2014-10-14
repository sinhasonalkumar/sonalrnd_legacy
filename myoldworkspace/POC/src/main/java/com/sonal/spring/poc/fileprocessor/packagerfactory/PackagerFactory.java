package com.sonal.spring.poc.fileprocessor.packagerfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sonal.spring.poc.fileprocessor.packagers.IPackager;
import com.sonal.spring.poc.util.ApplicationContextUtil;

@Component
public class PackagerFactory implements IPackagerFactory{

	@Override
	public IPackager getPackager(String packagerName) {
		ApplicationContext ctx = ApplicationContextUtil.getInstance().getApplicationContext();
		IPackager packager =  (IPackager) ctx.getBean(packagerName);
		return packager;
	}
	
	

}
