package com.sonal.controller;

import org.apache.log4j.Logger;

import com.sonal.services.facade.Facade;

public class CustomerController {
	static final Logger logger = Logger.getLogger(CustomerController.class);
	public static  void execute(){
		logger.debug("Sample debug message in CustomerController");
		logger.info("Sample info message in CustomerController");
		logger.warn("Sample warn message in CustomerController");
		logger.error("Sample error message in CustomerController");
		logger.fatal("Sample fatal message in CustomerController");
		Facade.execute();
	}
	
	public static void main(String[] args) {
		execute();
	}
}
