package com.sonal.spring.services;

import com.sonal.spring.components.SonalsLogger;


public interface CustomerService {
	@SonalsLogger(level = "error")
	void addCustomerAround(String name);
}