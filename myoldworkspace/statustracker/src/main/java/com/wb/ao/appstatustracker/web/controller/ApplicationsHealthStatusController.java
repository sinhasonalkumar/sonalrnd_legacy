package com.wb.ao.appstatustracker.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.ao.appstatustracker.services.facade.AOApplicationsStatusFinder;

@Controller
public class ApplicationsHealthStatusController {

	@Autowired 
	private AOApplicationsStatusFinder aoApplicationsStatusFinder;
	
	@RequestMapping(value = "/aoApplicationsStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Map<String, Map<String, String>> checkAOApplicationsHelthStatus(){
		Map<String, Map<String, String>> aoApplicationsStatus = new HashMap<String, Map<String, String>>();
		aoApplicationsStatus = aoApplicationsStatusFinder.checkHealthStatus();
		return aoApplicationsStatus;
	}
}
