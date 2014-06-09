package com.sonal.rnd.testSpring4.services;

import org.springframework.stereotype.Component;

@Component
public class ServiceImpl implements IService {

	public void dosomething() {
		System.out.println("Doing Something....");
	}

	

}
