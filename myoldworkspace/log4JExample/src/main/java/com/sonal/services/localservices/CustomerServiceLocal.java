package com.sonal.services.localservices;


import org.apache.log4j.Logger;

import com.sonal.dao.CustomerDAO;

public class CustomerServiceLocal {

	static final Logger logger = Logger.getLogger(CustomerServiceLocal.class);
	public static void execute(){
		logger.debug("Sample debug message in CustomerServiceLocal");
		logger.info("Sample info message in CustomerServiceLocal");
		logger.warn("Sample warn message in CustomerServiceLocal");
		logger.error("Sample error message in CustomerServiceLocal");
		logger.fatal("Sample fatal message in CustomerServiceLocal");
		CustomerDAO.execute();
	}
}
