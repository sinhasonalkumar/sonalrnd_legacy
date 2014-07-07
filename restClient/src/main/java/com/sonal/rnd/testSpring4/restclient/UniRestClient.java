package com.sonal.rnd.testSpring4.restclient;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

@Service
public class UniRestClient {

	public void getCall() throws Exception {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/springREST/helloWorldRESTGETService")
													.header("accept", "application/json")
													// .field("parameter", "value")
													.asJson();
		System.out.println("####################");
		System.out.println(jsonResponse.getCode());
		System.out.println(jsonResponse.getBody());
		System.out.println("####################");
	}

	public void postCall() throws Exception {
		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/springREST/helloWorldRESTPOSTService")
				.header("accept", "application/json")
						// .field("parameter", "value")
				.asJson();

		System.out.println("####################");
		System.out.println(jsonResponse.getCode());
		System.out.println(jsonResponse.getBody());
		System.out.println("####################");
	}

}
