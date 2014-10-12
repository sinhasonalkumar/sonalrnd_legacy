package com.sonal.rnd.testSpring4.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sonal.rnd.testSpring4.utility.J2JConverter;
import com.sonal.rnd.testSpring4.utility.RestResponse;

@Service
public class UniAsyncRESTClient {

	public void getCall() throws Exception {
		Future<HttpResponse<JsonNode>> futureResponse = Unirest.get("http://localhost:8080/springREST/helloWorldRESTGETService").header("accept", "application/json")
		// .field("param1", "value1")
		// .field("param2", "value2")
		.asJsonAsync(new Callback<JsonNode>() {

			public void failed(UnirestException e) {
				System.out.println("The request has failed");
			}

			public void completed(HttpResponse<JsonNode> response) {

				System.out.println("####################");
				System.out.println(response.getCode());
				System.out.println(response.getBody());
				System.out.println("####################");
			}

			public void cancelled() {
				System.out.println("The request has been cancelled");
			}

		});

	}

	public void postCall() throws Exception {
		Future<HttpResponse<JsonNode>> futureResponse = Unirest.post("http://localhost:8080/springREST/helloWorldRESTPOSTService").header("accept", "application/json")
		// .field("param1", "value1")
		// .field("param2", "value2")
		.asJsonAsync(new Callback<JsonNode>() {

			public void failed(UnirestException e) {
				System.out.println("The request has failed");
			}

			public void completed(HttpResponse<JsonNode> response) {

				System.out.println("####################");
				System.out.println(response.getCode());
				System.out.println(response.getBody());
				System.out.println("####################");
			}

			public void cancelled() {
				System.out.println("The request has been cancelled");
			}

		});

	}

	public void postCallDotNet() throws Exception {

		Map<String, String> requestHeaders = new HashMap<String, String>();

		requestHeaders.put("content-type", "application/json-rpc");
		requestHeaders.put("accept", "application/json-rpc");
		
		String jsonBody = "";
		InheritableThreadLocal<String> jsonResponse = new InheritableThreadLocal<String>();

		Future<HttpResponse<JsonNode>> futureResponse = Unirest.post("http://").headers(requestHeaders).body(jsonBody).asJsonAsync(new Callback<JsonNode>() {

			public void failed(UnirestException e) {
				System.out.println("The request has failed");
			}

			public void completed(HttpResponse<JsonNode> response) {

				System.out.println("####################");
				System.out.println(response.getCode());
				System.out.println(response.getBody());
				System.out.println("####################");
				
			}

			public void cancelled() {
				System.out.println("The request has been cancelled");
			}

		});
		
	
		
			
	}

	public RestResponse invokeREST(String restURL, Map<String, String> requestHeaders, final Class<?> requestVO, final Class<?> responseVO) throws Exception {
		RestResponse restResponse = new RestResponse();
		Object responseObject= null;

		String jsonBody = J2JConverter.ObjectToJson(requestVO, true);

		Future<HttpResponse<JsonNode>> futureResponse = Unirest.post(restURL).headers(requestHeaders).body(jsonBody).asJsonAsync(new Callback<JsonNode>() {

			public void failed(UnirestException e) {
				System.out.println("The request has failed");
			}

			public void completed(HttpResponse<JsonNode> response) {

				System.out.println("####################");
				System.out.println(response.getCode());
				JsonNode responseBody = response.getBody();
				
				System.out.println("####################");
				
			}

			public void cancelled() {
				System.out.println("The request has been cancelled");
			}

		});
		
		HttpResponse<JsonNode> httpResponse = futureResponse.get();
		JsonNode responseBody = httpResponse.getBody();
		String jsonResponse = responseBody.getObject().toString();
		System.out.println(responseBody);
		
		try {
			responseObject = J2JConverter.jsonToObject(jsonResponse, responseVO);
			
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		restResponse.setResponseVO(responseObject);
		restResponse.setResponse(httpResponse);

		return restResponse;
	}
}
