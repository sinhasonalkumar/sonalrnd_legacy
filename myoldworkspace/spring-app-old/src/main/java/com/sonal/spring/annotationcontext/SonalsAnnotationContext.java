package com.sonal.spring.annotationcontext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class SonalsAnnotationContext {
	
	private static Map<String, String> configutaions = new ConcurrentHashMap<String, String>();
	
	private SonalsAnnotationContext() {
		
	}
	
	public static void setConfiguration(String key, String value){
		if(!configutaions.containsKey(key)){
			configutaions.put(key, value);
		}
	}
	
	public static String getConfiguration(String key){
		return configutaions.get(key);
	}
	
	public static Map<String, String> getAllConfigs(){
		return configutaions;
	}

}
