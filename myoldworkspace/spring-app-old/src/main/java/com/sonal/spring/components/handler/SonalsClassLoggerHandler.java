package com.sonal.spring.components.handler;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sonal.spring.annotationcontext.SonalsAnnotationContext;
import com.sonal.spring.components.SonalsClassLogger;

@Component
public class SonalsClassLoggerHandler implements ApplicationContextAware, InitializingBean{
	
	  private ApplicationContext applicationContext;
	  
	  @Override
	  public void afterPropertiesSet() throws Exception {
	 
	    final Map<String, Object> myAnnotationClass = applicationContext.getBeansWithAnnotation(SonalsClassLogger.class);
	   
	 
	    for (final Object curAnnotationClass : myAnnotationClass.values()) {
	      final Class<? extends Object> clazzes = curAnnotationClass.getClass();
	      final SonalsClassLogger annotation = clazzes.getAnnotation(SonalsClassLogger.class);
	      System.out.println("Found Class: " + clazzes + ", with parameter: " + annotation.level());
	      SonalsAnnotationContext.setConfiguration(clazzes.getName(), annotation.level());
	      System.out.println(SonalsAnnotationContext.getAllConfigs().size());
	      System.out.println(SonalsAnnotationContext.getAllConfigs());
	    }
	  }
	 
	  @Override
	  public void setApplicationContext(final ApplicationContext applicationContext)
	      throws BeansException {
	    this.applicationContext = applicationContext;
	  }

}
