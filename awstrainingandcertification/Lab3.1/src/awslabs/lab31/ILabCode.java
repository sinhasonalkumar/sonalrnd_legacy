/**
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 * 
 * http://aws.amazon.com/apache2.0/
 * 
 * or in the "LICENSE" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package awslabs.lab31;

import java.util.List;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;

/**
 * Project: Lab3.1
 */
public interface ILabCode {
	String createQueue(AmazonSQSClient sqsClient, String queueName);
	String getQueueArn(AmazonSQSClient sqsClient, String queueUrl);
	String createTopic(AmazonSNSClient snsClient, String topicName);
	void createSubscription(AmazonSNSClient snsClient, String queueArn, String topicArn);
	void publishTopicMessage(AmazonSNSClient snsClient, String topicArn, String subject, String message);
	void postToQueue(AmazonSQSClient sqsClient, String queueUrl, String messageText);
	List<Message> readMessages(AmazonSQSClient sqsClient, String queueUrl);
	void removeMessage(AmazonSQSClient sqsClient, String queueUrl, String receiptHandle);
	void deleteSubscriptions(AmazonSNSClient snsClient, String topicArn);
	void deleteTopic(AmazonSNSClient snsClient, String topicArn);
	void deleteQueue(AmazonSQSClient sqsClient, String queueUrl);
}
