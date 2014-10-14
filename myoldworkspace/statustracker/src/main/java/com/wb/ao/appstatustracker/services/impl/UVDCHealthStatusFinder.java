package com.wb.ao.appstatustracker.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.wb.ao.appstatustracker.services.iface.HealthStatusFinder;

@Service("uvdcHealthStatusFinder")
public class UVDCHealthStatusFinder implements HealthStatusFinder {

	public Map<String, String> checkHealthStatus() {
		Document doc;
		Map<String, String> uvdcHealthStatus = new HashMap<String, String>();
		try {
			
			doc = Jsoup.connect("http://uvdc.warnerbros.com/uvdc/HealthCheckUVGatewayConnectivity").get();			
			String wholeContent = doc.body().text();
			String[] split = wholeContent.split("-------------------------------------");
			String test = split[1];			
			if (test.contains("PASS")) {
				uvdcHealthStatus.put("PASSED","Up And Running !!");
			} else {
				String[] failedText = test.split(":");
				String failedMsg = failedText[1];
				System.out.println("Down !!");
				failedMsg = failedMsg.replace("Output", " :: Failed !!");				
				uvdcHealthStatus.put("FAILED",failedMsg);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return uvdcHealthStatus;
	}

}
