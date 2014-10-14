package com.sonal.spring.test.manager;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sonal.spring.test.annotation.ConfigureConfiguration;
import com.sonal.spring.test.service.ConfigurationService;

@Component
public class ConfigurationManager {

	@Autowired()	
	private ConfigurationService configurationService;
	
	public void buildConfiguration(Object obj)  throws Exception{
		Class<? extends Object> currentCLass = obj.getClass();
		Field[] declaredFields = currentCLass.getDeclaredFields();
		
		for(Field currentFiled : declaredFields){
			ConfigureConfiguration configureAnnotation = currentFiled.getAnnotation(ConfigureConfiguration.class);
			if(configureAnnotation != null){
				String key = configureAnnotation.key();
				String source = configureAnnotation.source();
				String value = getValue(key,source);				
				Method currentMethod = getMethod(currentFiled.getName(), currentCLass, obj);
				currentMethod.invoke(obj, value);				
			}
		}
				
	}
	
	private  String getValue(String key, String source){		
		 Map<String, String> configMap = (Map<String, String>)configurationService.getConfiguration(source);
		 return configMap.get(key);
		
	}
	
	private static Method getMethod(String fieldName,Class<? extends Object> currentCLass,Object obj){
		Method method = null;
		String methodName = "set"+fieldName;
		Method[] methods = currentCLass.getMethods();
		
		for(Method curMethod : methods){
			if(curMethod.getName().equalsIgnoreCase(methodName)){
				method = curMethod;
			}
		}
		
		return method;
	}

	public ConfigurationService getConfigurationSource() {
		return configurationService;
	}

	public void setConfigurationSource(ConfigurationService configurationSource) {
		this.configurationService = configurationSource;
	}

	
	
	 
	
	
}
