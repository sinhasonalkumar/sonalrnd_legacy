package com.sonal.spring.dynamicloading;

import org.springframework.stereotype.Component;

@Component
public class TestDynamicPropClass {

	@SonalsDynamicProLoader
	public void doSomething(){
		System.out.println("doing something");
	}
}
