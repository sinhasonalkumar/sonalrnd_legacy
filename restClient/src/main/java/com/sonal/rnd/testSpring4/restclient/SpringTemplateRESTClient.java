package com.sonal.rnd.testSpring4.restclient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonal.rnd.testSpring4.restVO.StudentResponseVO;

@Service
public class SpringTemplateRESTClient {

	@Autowired
	private RestTemplate restTemplate;

	public void getCall() throws Exception {

		ResponseEntity<StudentResponseVO> response = restTemplate.getForEntity("http://localhost:8080/springREST/helloWorldRESTGETService", StudentResponseVO.class);
		StudentResponseVO studentResponseVO = response.getBody();
		System.out.println("####################");
		System.out.println(response.getStatusCode());
		System.out.println(studentResponseVO.getName());
		System.out.println(studentResponseVO.getAge());
		System.out.println("####################");
	}

	public void postCall() throws Exception {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("testKey", "I am REST Template Client");
		ResponseEntity<StudentResponseVO> response = restTemplate.postForEntity("http://localhost:8080/springREST/helloWorldRESTPOSTService", requestMap,StudentResponseVO.class);
		StudentResponseVO studentResponseVO = response.getBody();
		System.out.println("####################");
		System.out.println(response.getStatusCode());
		System.out.println(studentResponseVO.getName());
		System.out.println(studentResponseVO.getAge());
		System.out.println("####################");
	}

}
