package com.sonal.services;

import org.springframework.stereotype.Component;

import com.sonal.spring.annotationcontext.SonalsAnnotationContext;
import com.sonal.spring.components.SonalsLogger;

@Component
public class Service1 {
	
	@SonalsLogger(level = "myLevelMethod")
	public void showAllConfigs(){
		SonalsAnnotationContext.setConfiguration("Service1", "DEV-Service");
		System.out.println(SonalsAnnotationContext.getAllConfigs().size());
		System.out.println(SonalsAnnotationContext.getAllConfigs());
	}

}
