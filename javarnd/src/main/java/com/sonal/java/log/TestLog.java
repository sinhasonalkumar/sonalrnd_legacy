package com.sonal.java.log;

import org.apache.log4j.Logger;

public class TestLog {

	static Logger logger = Logger.getLogger(TestLog.class);
	
	public static void main(String[] args) {
		logger.debug("Testing Info");
	}
}
