package org.zagile.quiz.business;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

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
			//Path path = new Path("/");
			//FileStatus[] listStatus = fs.listStatus(path);
			//logger.info("=======>" + listStatus.length);
			Path path = new Path(dfs.getWorkingDirectory().toString());
			
			FileStatus[] filesFirstLevel = dfs.listStatus(path); // first level
			
			for(FileStatus fileFL : filesFirstLevel){
				System.out.println(fileFL.getPath().getName());
				if (fileFL.isDir()) { // second level
					Path pathSL = new Path(fileFL.getPath().toString());
					FileStatus[] filesSecondLevel = dfs.listStatus(pathSL);
					for(FileStatus fileSL : filesSecondLevel){
						System.out.println("\t" + fileSL.getPath().getName());
					}
				}
			}
			dfs.close();
			return "JSONFORMAT";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
