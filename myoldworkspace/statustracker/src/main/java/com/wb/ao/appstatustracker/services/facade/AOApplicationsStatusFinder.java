package com.wb.ao.appstatustracker.services.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wb.ao.appstatustracker.servicelocator.HealthCheckServiceLocator;

@Component
public class AOApplicationsStatusFinder implements ApplicationsStatusFinder {	
	
	@Autowired
	private HealthCheckServiceLocator healthCheckServiceLocator;
	
	public Map<String, Map<String, String>> checkHealthStatus() {
		
		Map<String, Map<String, String>> applicationsHealthStatus = new HashMap<String, Map<String, String>>();
		Map<String, String> uvgsHealthStatus = healthCheckServiceLocator.getUvgsHealthStatusFinder().checkHealthStatus();
		Map<String, String> uvdcHealthStatus = healthCheckServiceLocator.getUvdcHealthStatusFinder().checkHealthStatus();
		Map<String, String> publisherHealthStatus = healthCheckServiceLocator.getPublisherHealthStatusFinder().checkHealthStatus();
		
		applicationsHealthStatus.put("UVGS", uvgsHealthStatus);
		applicationsHealthStatus.put("UVDC", uvdcHealthStatus);
		applicationsHealthStatus.put("PUBLISHER", publisherHealthStatus);
		
		return applicationsHealthStatus;
	}
	
}
