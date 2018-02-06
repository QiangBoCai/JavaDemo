package com.lance.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.Logger;

public class Log4jDemo {

	private static  Logger logger = LoggerFactory.getLogger(Log4jDemo.class);
	
	public static void main(String[] args) 
	{
		logger.debug("Debug message");
		logger.info("Info Message");
		logger.warn("Warn Message");
		logger.error("Error Message");
		
	}

}
