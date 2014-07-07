package com.sonal.rnd.testSpring4.restclient;

import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

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
}
