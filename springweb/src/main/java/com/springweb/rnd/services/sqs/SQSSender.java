package com.springweb.rnd.services.sqs;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Component
public class SQSSender { 

	public void sendMessage(){
		String accessId = "AKIAJRXAAND4DCIHQVOQ";
		String secretKey = "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";
		AWSCredentials credentials = new BasicAWSCredentials(accessId,secretKey);
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		sqs.sendMessage(new SendMessageRequest("https://sqs.us-east-1.amazonaws.com/868630891305/wbid_dev", "TestSQSMessage"));
	}

}
