package org.zagile.quiz.business;

import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service("dataRetriever")
public class DataRetriever implements IDataRetriever {

	private static Logger logger = Logger.getLogger(DataRetriever.class);
	
	private Configuration conf;
	private FileSystem dfs;
	
	public String retriveFileSystem() {
		try {
			logger.info("Starting file system reading ...");
			
			conf = new Configuration();
			conf.set("fs.default.name", "hdfs://localhost:54310");
			dfs = FileSystem.get(conf);
			
			Path path = new Path(dfs.getWorkingDirectory().toString());
			FileStatus[] filesFirstLevel = dfs.listStatus(path); // first level
			HashMap<String, String> result = new HashMap<String, String>();
			for(FileStatus fileFL : filesFirstLevel){
				if (fileFL.isDir()) { // second level
					logger.info("First Level. Reading a directory : " + fileFL.getPath().getName());
					Path pathSL = new Path(fileFL.getPath().toString());
					FileStatus[] filesSecondLevel = dfs.listStatus(pathSL);
					String value = "";
					for(FileStatus fileSL : filesSecondLevel){
						logger.info("Second Level. Reading ... : " + fileSL.getPath().getName());
						value += fileSL.getPath().getName() + ",";
					}
					result.put(fileFL.getPath().getName(), value);
				} else {
					logger.info("First Level. Reading a file : " + fileFL.getPath().getName());
					result.put(fileFL.getPath().getName(), "");
				}
			}
			dfs.close();
			logger.info("Converting the result to JSON format.");
			return (new Gson()).toJson(result);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
