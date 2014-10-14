package com.sonal.services.remoteservices;

import org.apache.log4j.Logger;

public class CustomerWebService {
	static final Logger logger = Logger.getLogger(CustomerWebService.class);
	public static void  execute(){
		logger.debug("Sample debug message in CustomerWebService");
		logger.info("Sample info message in CustomerWebService");
		logger.warn("Sample warn message in CustomerWebService");
		logger.error("Sample error message in CustomerWebService");
		logger.fatal("Sample fatal message in CustomerWebService");
	}
}
