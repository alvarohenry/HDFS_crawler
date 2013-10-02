package org.zagile.quiz.business;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("dataRetriever")
public class DataRetriever implements IDataRetriever {

	private static Logger logger = Logger.getLogger(DataRetriever.class);
	
	public String retriveFileSystem() {
		logger.info("Starting file system reading ...");
		return "JSONFORMAT";
	}
	
}
