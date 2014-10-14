package com.wb.ao.appstatustracker.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.wb.ao.appstatustracker.services.iface.HealthStatusFinder;

@Service("uvgsHealthStatusFinder")
public class UVGSHealthStatusFinder implements HealthStatusFinder{

	public Map<String, String> checkHealthStatus() {
		Document doc;
		Map<String, String> uvgsHealthStatus = new HashMap<String, String>();
		try {
			
			doc = Jsoup.connect("http://ultraviolet.warnerbros.com/uvservice/HealthCheckDatabaseConnectivity").get();			
			String wholeContent = doc.body().text();
			String[] split = wholeContent.split("-------------------------------------");
			String test = split[1];			
			if (test.contains("PASS")) {
				uvgsHealthStatus.put("PASSED","Up And Running !!");
			} else {
				String[] failedText = test.split(":");
				String failedMsg = failedText[1];
				System.out.println("Down !!");
				failedMsg = failedMsg.replace("Output", " :: Failed !!");				
				uvgsHealthStatus.put("FAILED",failedMsg);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return uvgsHealthStatus;
	}	
}
