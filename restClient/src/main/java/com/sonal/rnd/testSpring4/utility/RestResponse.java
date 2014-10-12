package com.sonal.rnd.testSpring4.utility;

import lombok.Getter;
import lombok.Setter;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

@Getter
@Setter
public class RestResponse {

	private Object responseVO;
	
	private HttpResponse<JsonNode> response;
}
