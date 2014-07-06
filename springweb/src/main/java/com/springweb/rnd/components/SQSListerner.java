package com.springweb.rnd.components;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

@Component
public class SQSListerner implements ApplicationListener<ContextRefreshedEvent> {

	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// ApplicationContext applicationContext =
		// contextStartedEvent.getApplicationContext();
		// SQSReceiver sqsReceiver = (SQSReceiver)
		// applicationContext.getBean(SQSReceiver.class);
		// sqsReceiver.receiveMessage();

		//System.out.println(" ContextStartedEventListener received " + event.getClass() + "\n at " + event.getTimestamp() + "\n with Source as " + event.getSource().getClass() + "\n for application context " + event.getApplicationContext().getClass());

		

		

		receiveMessage();

	}
	@Scheduled(fixedRate = 10000)
	public void receiveMessage() {
		String accessId = "";
		String secretKey = "";
		AWSCredentials credentials = new BasicAWSCredentials(accessId, secretKey);
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		ReceiveMessageRequest request = new ReceiveMessageRequest("SQS End POINT");
		ReceiveMessageResult result = sqs.receiveMessage(request);
		List<Message> messages = result.getMessages();
		
			System.out.println("Looking For New Message");
			for (com.amazonaws.services.sqs.model.Message message : messages) {
				System.out.println("receiving message " + message.getMessageId());
				System.out.println("Message Body" + message.getBody());

				DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest("SQS End POINT", message.getReceiptHandle());
				sqs.deleteMessage(deleteMessageRequest);
			}
		

	}

}
