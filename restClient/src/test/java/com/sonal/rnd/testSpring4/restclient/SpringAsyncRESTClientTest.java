package com.sonal.rnd.testSpring4.restclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sonal.rnd.testSpring4.app.ApplicationContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
// @PropertySource
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class SpringAsyncRESTClientTest {

	@Autowired
	private SpringAsyncRESTClient springAsyncRESTClient;

	@Test
	public void testGetAsyncCall() throws Exception {
		springAsyncRESTClient.getAsyncCall();
	}

	@Test
	public void testGetAsyncCallWithCallback() throws Exception {
		springAsyncRESTClient.getAsyncCallWithCallback();
	}
	
	@Test
	public void testPostAsyncCall() throws Exception {
		springAsyncRESTClient.postAsyncCall();
	}

	@Test
	public void testPostAsyncCallWithCallback() throws Exception {
		springAsyncRESTClient.postAsyncCallWithCallback();
	}

}
