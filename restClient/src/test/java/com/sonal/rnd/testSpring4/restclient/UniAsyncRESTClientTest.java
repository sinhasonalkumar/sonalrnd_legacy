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
public class UniAsyncRESTClientTest {

	@Autowired
	private UniAsyncRESTClient uniAsyncRESTClient;
	
	@Test
	public void testGetCall() throws Exception{
		uniAsyncRESTClient.getCall();
	}
	
	@Test
	public void testPostCall() throws Exception{
		uniAsyncRESTClient.postCall();
	}

}
