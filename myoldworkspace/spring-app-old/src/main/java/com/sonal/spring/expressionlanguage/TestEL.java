package com.sonal.spring.expressionlanguage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestEL {
	
	@Value("#{cacheClass.config.get(\"test\")}")
	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	

	

}
