package com.sonal.services.mqservices;

import org.apache.log4j.Logger;

public class CustomerMQService {
	static final Logger logger = Logger.getLogger(CustomerMQService.class);
	public static void  execute(){
		logger.debug("Sample debug message in MQService");
		logger.info("Sample info message in MQService");
		logger.warn("Sample warn message in MQService");
		logger.error("Sample error message in MQService");
		logger.fatal("Sample fatal message in MQService");
	}
}
