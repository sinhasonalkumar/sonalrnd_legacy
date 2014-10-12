package com.sonal.rnd.testSpring4.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class SimpleHttpClient {

	public static void main(String[] args) throws Exception {
		String url = "http:///";
		URL myurl = new URL(url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);

		request.addHeader("content-type", "application/json-rpc");
		request.addHeader("accept", "application/json-rpc");
		

		String jsonBody = "";
		StringEntity requestEntity = new StringEntity(jsonBody);

		request.setEntity(requestEntity);

		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("StatusCode :: " + statusCode);

		HttpEntity responseEntity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader((responseEntity.getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		client.getConnectionManager().shutdown();
	}

}
