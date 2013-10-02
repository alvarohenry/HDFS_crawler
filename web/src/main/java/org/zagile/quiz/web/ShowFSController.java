package org.zagile.quiz.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zagile.quiz.business.DataRetriever;

@Controller
@RequestMapping("/showfs")
public class ShowFSController {
	
	private static Logger logger = Logger.getLogger(ShowFSController.class);
	
	@Autowired
	private DataRetriever dataRetriever;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showFS(ModelMap model) {
		logger.info("Controller - showFS : Reading the Hadoop File System ....");
		String result = dataRetriever.retriveFileSystem();
		model.addAttribute("messages", "Spring 3 MVC " + result);
		return "hello";
	}
}
