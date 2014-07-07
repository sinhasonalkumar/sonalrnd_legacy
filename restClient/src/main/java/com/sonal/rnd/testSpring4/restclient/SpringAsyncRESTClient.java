package com.sonal.rnd.testSpring4.restclient;

import java.util.Arrays;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import com.sonal.rnd.testSpring4.restVO.StudentResponseVO;

@Service
public class SpringAsyncRESTClient {

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	public void getAsyncCall() throws Exception {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		// requestHeaders.add("Cookie", cookie);

		System.out.println(requestHeaders);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", requestHeaders);

		Future<ResponseEntity<StudentResponseVO>> futureEntity = asyncRestTemplate.exchange("http://localhost:8080/springREST/helloWorldRESTGETService", HttpMethod.GET, entity, StudentResponseVO.class, "0");

		System.out.println("Doing other async stuff...");

		System.out.println("Blocking to receive response...");
		ResponseEntity<StudentResponseVO> response = null;

		response = futureEntity.get();
		System.out.println("Response received");
		System.out.println("####################");
		StudentResponseVO studentVO = response.getBody();
		response.getStatusCode();
		System.out.println(studentVO.getName());
		System.out.println(studentVO.getAge());
		System.out.println("####################");

	}

	public void getAsyncCallWithCallback() throws Exception {

		AsyncRestTemplate restTemplate = new AsyncRestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		//requestHeaders.add("Cookie", cookie);

		System.out.println(requestHeaders);

		HttpEntity entity = new HttpEntity("parameters", requestHeaders);

		ListenableFuture<ResponseEntity<StudentResponseVO>> futureEntity = restTemplate.exchange("http://localhost:8080/springREST/helloWorldRESTGETService", HttpMethod.GET, entity, StudentResponseVO.class, "0");

		futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<StudentResponseVO>>() {

			

			public void onSuccess(ResponseEntity<StudentResponseVO> response) {
				StudentResponseVO studentResponseVO = response.getBody();
				System.out.println("####################");
				System.out.println(response.getStatusCode());
				System.out.println(studentResponseVO.getName());
				System.out.println(studentResponseVO.getAge());
				System.out.println("####################");
				
			}
			
			public void onFailure(Throwable arg0) {
				System.out.println("Async Get Call Failed ...");
				
			}
			
			
			
		});

		System.out.println("Doing other async callable calls ...");

	}

	public void postAsyncCall() throws Exception {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		// requestHeaders.add("Cookie", cookie);

		System.out.println(requestHeaders);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", requestHeaders);

		Future<ResponseEntity<StudentResponseVO>> futureEntity = asyncRestTemplate.exchange("http://localhost:8080/springREST/helloWorldRESTPOSTService", HttpMethod.POST, entity, StudentResponseVO.class, "0");

		System.out.println("Doing other async stuff...");

		System.out.println("Blocking to receive response...");
		ResponseEntity<StudentResponseVO> response = null;

		response = futureEntity.get();
		System.out.println("Response received");
		System.out.println("####################");
		StudentResponseVO studentVO = response.getBody();
		response.getStatusCode();
		System.out.println(studentVO.getName());
		System.out.println(studentVO.getAge());
		System.out.println("####################");

	}

	public void postAsyncCallWithCallback() throws Exception {

		AsyncRestTemplate restTemplate = new AsyncRestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		//requestHeaders.add("Cookie", cookie);

		System.out.println(requestHeaders);

		HttpEntity entity = new HttpEntity("parameters", requestHeaders);

		ListenableFuture<ResponseEntity<StudentResponseVO>> futureEntity = restTemplate.exchange("http://localhost:8080/springREST/helloWorldRESTPOSTService", HttpMethod.POST, entity, StudentResponseVO.class, "0");

		futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<StudentResponseVO>>() {

			

			public void onSuccess(ResponseEntity<StudentResponseVO> response) {
				StudentResponseVO studentResponseVO = response.getBody();
				System.out.println("####################");
				System.out.println(response.getStatusCode());
				System.out.println(studentResponseVO.getName());
				System.out.println(studentResponseVO.getAge());
				System.out.println("####################");
				
			}
			
			public void onFailure(Throwable arg0) {
				System.out.println("Async Get Call Failed ...");
				
			}
			
			
			
		});

		System.out.println("Doing other async callable calls ...");
	}

}
