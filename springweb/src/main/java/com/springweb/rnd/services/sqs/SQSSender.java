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
		String accessId = "";
		String secretKey = "";
		AWSCredentials credentials = new BasicAWSCredentials(accessId,secretKey);
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		sqs.sendMessage(new SendMessageRequest("SQS END POINT", "TestSQSMessage"));
	}

}
