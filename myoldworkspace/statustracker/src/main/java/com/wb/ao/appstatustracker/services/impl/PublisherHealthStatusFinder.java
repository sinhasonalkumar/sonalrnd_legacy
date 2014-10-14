package com.wb.ao.appstatustracker.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wb.ao.appstatustracker.services.iface.HealthStatusFinder;

@Service("publisherHealthStatusFinder")
public class PublisherHealthStatusFinder implements HealthStatusFinder{

	@Autowired
	private RestTemplate restTemplate;
	
	public Map<String, String> checkHealthStatus() {
		
		Map<String, String> publisherHealthStatus = new HashMap<String, String>();
		try {
			String sayHelloGetResponse = restTemplate.getForObject("http://content.wbp.warnerbros.com/wbp-content/APPDATA/HEALTH", String.class);
			System.out.println(sayHelloGetResponse);
			if(sayHelloGetResponse!= null && sayHelloGetResponse.contains("OK")){
				publisherHealthStatus.put("PASSED", "Up And Running !!");
			}else{
				publisherHealthStatus.put("FAILED", "Database Report Failed !!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publisherHealthStatus;
	}	
	
}
