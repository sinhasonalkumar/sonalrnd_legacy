package com.springweb.rnd.services.sqs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;


@Component
public class SQSReceiver {

	
	
	public void receiveMessage(){
		String accessId = "AKIAJRXAAND4DCIHQVOQ";
		String secretKey = "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";
		AWSCredentials credentials = new BasicAWSCredentials(accessId,secretKey);
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		ReceiveMessageRequest request = new ReceiveMessageRequest("https://sqs.us-east-1.amazonaws.com/868630891305/wbid_dev");
		ReceiveMessageResult result = sqs.receiveMessage(request);
		List<com.amazonaws.services.sqs.model.Message> messages = result.getMessages();

        for (com.amazonaws.services.sqs.model.Message message : messages) {
            System.out.println("receiving message " + message.getMessageId());
            System.out.println("Message Body" + message.getBody());
            
            DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest("https://sqs.us-east-1.amazonaws.com/868630891305/wbid_dev",message.getReceiptHandle());
            sqs.deleteMessage(deleteMessageRequest);
        }
        
        
	}

}
