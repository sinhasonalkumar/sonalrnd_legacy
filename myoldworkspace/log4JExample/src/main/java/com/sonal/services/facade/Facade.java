package com.sonal.services.facade;

import org.apache.log4j.Logger;

import com.sonal.services.localservices.CustomerServiceLocal;
import com.sonal.services.mqservices.CustomerMQService;
import com.sonal.services.remoteservices.CustomerWebService;

public class Facade {
	static final Logger logger = Logger.getLogger(Facade.class);
	public static void execute(){
		logger.debug("Sample debug message in Facade");
		logger.info("Sample info message in Facade");
		logger.warn("Sample warn message in Facade");
		logger.error("Sample error message in Facade");
		logger.fatal("Sample fatal message in Facade");
		CustomerServiceLocal.execute();
		CustomerWebService.execute();
		CustomerMQService.execute();
		CustomerWebService.execute();
	}
}
