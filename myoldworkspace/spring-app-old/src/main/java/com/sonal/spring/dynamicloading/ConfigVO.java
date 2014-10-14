package com.sonal.spring.dynamicloading;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigVO {

	@Autowired
	PropertiesConfiguration configuration;
	
	public String getDetailLoggingAspect() throws Exception {			
		String value = (String) configuration.getProperty("DetailLoggingAspect");		
		return value;
	 
	}
}
