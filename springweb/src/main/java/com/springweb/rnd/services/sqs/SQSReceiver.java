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
		String accessId = "";
		String secretKey = "";
		AWSCredentials credentials = new BasicAWSCredentials(accessId,secretKey);
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		ReceiveMessageRequest request = new ReceiveMessageRequest("SQS END POINT");
		ReceiveMessageResult result = sqs.receiveMessage(request);
		List<com.amazonaws.services.sqs.model.Message> messages = result.getMessages();

        for (com.amazonaws.services.sqs.model.Message message : messages) {
            System.out.println("receiving message " + message.getMessageId());
            System.out.println("Message Body" + message.getBody());
            
            DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest("SQS END POINT",message.getReceiptHandle());
            sqs.deleteMessage(deleteMessageRequest);
        }
        
        
	}

}
