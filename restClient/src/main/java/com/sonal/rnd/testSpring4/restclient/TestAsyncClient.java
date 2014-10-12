package com.sonal.rnd.testSpring4.restclient;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

@Service
public class TestAsyncClient {

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	public void invoke() throws Exception {

		HttpHeaders requestHeaders = new HttpHeaders();
		
		requestHeaders.add("content-type", "application/json-rpc");
		requestHeaders.add("accept", "application/json-rpc");
		

		System.out.println(requestHeaders);

		String jsonString = "";
		HttpEntity<String> entity = new HttpEntity<String>(jsonString, requestHeaders);

		ListenableFuture<ResponseEntity<String>> futureEntity = asyncRestTemplate.exchange("http://", HttpMethod.POST, entity, String.class, "0");

		System.out.println("Doing other async stuff...");

		System.out.println("Blocking to receive response...");
		

		ResponseEntity<String> responseEntity = futureEntity.get();
		String body = responseEntity.getBody();
		
		System.out.println("Response received");
		System.out.println("####################");
		

	}
}
