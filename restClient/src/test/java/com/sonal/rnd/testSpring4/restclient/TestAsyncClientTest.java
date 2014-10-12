package com.sonal.rnd.testSpring4.restclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sonal.rnd.testSpring4.app.ApplicationContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@PropertySource
@ContextConfiguration(classes = {ApplicationContextConfig.class})
public class TestAsyncClientTest {

	@Autowired
	private TestAsyncClient testAsyncClient;
	
	@Test
	public void testRESTCall() throws Exception{
		testAsyncClient.invoke();	}
}
