package com.sonal.webserviceclient.client;



public interface WebServiceURI {
	
	String sayHelloGet = "http://localhost:8080/SpringREST/rest/helloGet/{name}";
	String sayHelloPost = "http://localhost:8080/SpringREST/rest/helloPost";
	String getAllStudents = "http://localhost:8080/SpringREST/rest/getAllStudents/";
	String getStudents = "http://localhost:8080/SpringREST/rest/getStudents/";
	

}
