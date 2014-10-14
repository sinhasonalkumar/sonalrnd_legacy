package com.sonal.dao;

import org.apache.log4j.Logger;

public class CustomerDAO {
	static final Logger logger = Logger.getLogger(CustomerDAO.class);
	public static void execute(){
		logger.debug("Sample debug message in CutomerDAO");
		logger.info("Sample info message in CutomerDAO");
		logger.warn("Sample warn message in CutomerDAO");
		logger.error("Sample error message in CutomerDAO");
		logger.fatal("Sample fatal message in CutomerDAO");
	}
}
