package com.springweb.rnd.rest.webservices;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Assert;
import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.Header;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class SpringRESTWebServiceTest {

	@Rule
	public Destination destination = new Destination("http://localhost:8080/springREST");

	@Context
	private Response response;

	@Before
	public void setup() {
		System.out.println("setup");
	}

	@HttpTest(method = Method.GET, path = "/helloWorldRESTGETService")
	public void testHelloWorldRESTGETService() {
		Assert.assertOk(response);
		String answerBody = response.getBody(String.class);
		System.out.println(answerBody);
	}

	// @HttpTest(method = Method.POST, path =
	// "/helloWorldRESTPOSTService",content ="body")
	// @HttpTest(method = Method.POST, path = "/helloWorldRESTPOSTService",file
	// ="data.xml")
	@HttpTest(method = Method.POST, path = "/helloWorldRESTPOSTService", headers = { @Header(name = "Accept", value = "Application/xml") })
	public void testHelloWorldRESTPOSTService() {
		Assert.assertOk(response);
		String answerBody = response.getBody(String.class);
		System.out.println(answerBody);
	}

}
