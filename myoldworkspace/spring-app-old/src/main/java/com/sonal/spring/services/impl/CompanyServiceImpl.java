package com.sonal.spring.services.impl;

import org.springframework.stereotype.Component;

import com.sonal.spring.components.SonalsClassLogger;
import com.sonal.spring.services.CompanyService;

@Component
@SonalsClassLogger(level = "Debug")
public class CompanyServiceImpl implements CompanyService {

	public void addCustomerAround(String name) {
		System.out.println("addCustomerAround() is running, args : " + name);
	}
}