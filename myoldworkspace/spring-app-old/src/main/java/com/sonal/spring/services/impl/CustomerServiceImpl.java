package com.sonal.spring.services.impl;



import org.springframework.stereotype.Component;

import com.sonal.spring.components.SonalsLogger;
import com.sonal.spring.services.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {
	
	
	@SonalsLogger(level = "info")
	public void addCustomerAround(String name){
		System.out.println("addCustomerAround() is running, args : " + name);
	}
}