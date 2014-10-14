package com.sonal.spring.expressionlanguage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CacheClass {

	private Map<String, String> config;

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}
	
	@PostConstruct
	public void initCacheClass(){
		config = new HashMap<String, String>();
		config.put("test", "impl11");
		config.put("test1", "impl1");
	}
}
