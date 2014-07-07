package com.sonal.rnd.testSpring4.restclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sonal.rnd.testSpring4.app.ApplicationContextConfig;
import com.sonal.rnd.testSpring4.restclient.UniRestClient;


@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@PropertySource
@ContextConfiguration(classes = {ApplicationContextConfig.class})
public class UniRestClientTest {
	
	@Autowired
	private UniRestClient uniRestClient;
	
	@Test
	public void testGetCall() throws Exception{
		uniRestClient.getCall();
	}
	
	@Test
	public void testPostCall() throws Exception{
		uniRestClient.postCall();
	}


}
