package com.springweb.rnd.rest.webservices;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springweb.rnd.services.memcache.TestElastiCacheAutoDiscovery;
import com.springweb.rnd.services.memcache.TestMemcache;
import com.springweb.rnd.services.sqs.SQSReceiver;
import com.springweb.rnd.services.sqs.SQSSender;

@Controller
public class TestWebService {

	@Autowired
	private TestMemcache testMemcache;
	@Autowired
	private TestElastiCacheAutoDiscovery testElastiCacheAutoDiscovery;
	@Autowired
	private SQSSender sqsSender;
	@Autowired
	private SQSReceiver sqsReceiver;

	@RequestMapping(value = "/helloWorldRESTService", method = RequestMethod.GET)
	public @ResponseBody
	String helloWorldRESTService() {
		System.out.println("helloWorldRESTService entered !!");

		return "Hello World !!";
	}

	@RequestMapping(value = "/testElastiCache", method = RequestMethod.GET)
	public @ResponseBody
	String testElastiCache() {
		System.out.println("testElastiCache entered !!");
		testMemcache.testCache();

		return "testElastiCache Worked !!";
	}

	@RequestMapping(value = "/testAddToElastiCache", method = RequestMethod.GET)
	public @ResponseBody
	String testAddToElastiCache() {
		System.out.println("testßAddToElastiCache entered !!");
		testMemcache.testAdd();

		return "testAddToElastiCache Worked !!";
	}

	@RequestMapping(value = "/testReadToElastiCache", method = RequestMethod.GET)
	public @ResponseBody
	String testReadFromElastiCache() {
		System.out.println("testReadToElastiCache entered !!");
		String testRead = testMemcache.testRead();

		return testRead;
	}

	@RequestMapping(value = "/testRemoveFromElastiCache", method = RequestMethod.GET)
	public @ResponseBody
	String testRemoveFromElastiCache() {
		System.out.println("testRemoveFromElastiCache entered !!");
		testMemcache.testRemove();

		return "testRemoveFromElastiCache Worked !!";
	}

	@RequestMapping(value = "/sendMessageToSQS", method = RequestMethod.GET)
	public @ResponseBody
	String sendMessageToSQS() {
		System.out.println("sendToSQS entered !!");
		try {
			sqsSender.sendMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "sendToSQS Worked !!";
	}

	@RequestMapping(value = "/receiveMessageFromSQS", method = RequestMethod.GET)
	public @ResponseBody
	String receiveMessageFromSQS() {
		System.out.println("receiveMessageFromSQS entered !!");
		try {
			sqsReceiver.receiveMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "receiveMessageFromSQS Worked !!";
	}

	@RequestMapping(value = "/addToElastiCacheAutoDiscovery", method = RequestMethod.GET)
	public @ResponseBody
	String addToElastiCacheAutoDiscovery() {
		System.out.println("addToElastiCacheAutoDiscovery entered !!");
		try {
			testElastiCacheAutoDiscovery.addToElastiCacheAutoDiscovery();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "testAddToElastiCache Worked !!";
	}
	
	@RequestMapping(value = "/removeFromElastiCacheAutoDiscovery", method = RequestMethod.GET)
	public @ResponseBody
	String removeFromElastiCacheAutoDiscovery() {
		System.out.println("removeFromElastiCacheAutoDiscovery entered !!");
		try {
			testElastiCacheAutoDiscovery.removeFromElastiCacheAutoDiscovery();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "testAddToElastiCache Worked !!";
	}
	
	@RequestMapping(value = "/getFromElastiCacheAutoDiscovery", method = RequestMethod.GET)
	public @ResponseBody
	String getFromElastiCacheAutoDiscovery() {
		System.out.println("getFromElastiCacheAutoDiscovery entered !!");
		String cachedElement = "Key Not Found In ElastiCache";
		try {
			cachedElement = testElastiCacheAutoDiscovery.getFromElastiCacheAutoDiscovery() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cachedElement;
	}

}
