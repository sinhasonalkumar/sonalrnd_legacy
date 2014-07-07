package com.springweb.rnd.rest.webservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringRESTWebService {

	@RequestMapping(value = "/helloWorldRESTService", method = RequestMethod.GET)
	public @ResponseBody
	String helloWorldRESTService() {
		System.out.println("helloWorldRESTService entered !!");

		return "Hello World !!";
	}

	@RequestMapping(value = "/testElastiCache", method = RequestMethod.GET)
	public @ResponseBody
	String testElastiCache() {
		
		return "testElastiCache Worked !!";
	}


}
