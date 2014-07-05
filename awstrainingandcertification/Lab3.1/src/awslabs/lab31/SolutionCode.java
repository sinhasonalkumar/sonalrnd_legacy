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

import java.util.HashMap;
import java.util.List;

import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.Statement.Effect;
import com.amazonaws.auth.policy.actions.SQSActions;
import com.amazonaws.auth.policy.conditions.ConditionFactory;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

/**
 * Project: Lab3.1
 */
public abstract class SolutionCode implements ILabCode, IOptionalLabCode {

	@Override
	public String createQueue(AmazonSQSClient sqsClient, String queueName) {
		// TODO: Construct a CreateQueueRequest object using the provided queue name.
		CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(queueName);

		// TODO: Submit the request using the createQueue method of the sqsClient object.
		CreateQueueResult createQueueResult = sqsClient.createQueue(createQueueRequest);

		// TODO: Return the queue URL from the request result.
		return createQueueResult.getQueueUrl();
	}

	@Override
	public String getQueueArn(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: Construct a GetQueueAttributesRequest for the specified queue and for the attribute named "QueueArn".
		GetQueueAttributesRequest getQueueAttributesRequest = new GetQueueAttributesRequest().withQueueUrl(queueUrl)
				.withAttributeNames("QueueArn");

		// TODO: Submit the request using the getQueueAttributes method of the sqsClient object.
		GetQueueAttributesResult getQueueAttributesResult = sqsClient.getQueueAttributes(getQueueAttributesRequest);

		// TODO: Return the QueueArn attribute value.
		return getQueueAttributesResult.getAttributes().get("QueueArn");
	}

	@Override
	public String createTopic(AmazonSNSClient snsClient, String topicName) {
		// TODO: Construct a CreateTopicRequest object using the specified topic name.
		CreateTopicRequest createTopicRequest = new CreateTopicRequest().withName(topicName);

		// TODO: Submit the request using the createTopic method of the snsClient object.
		CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);

		// TODO: Return the topic ARN from the request result.
		return createTopicResult.getTopicArn();
	}

	@Override
	public void createSubscription(AmazonSNSClient snsClient, String queueArn, String topicArn) {
		// TODO: Construct a SubscribeRequest object using the "sqs" protocol for the specified queue ARN endpoint and
		// topic ARN.
		SubscribeRequest subscribeRequest = new SubscribeRequest().withEndpoint(queueArn).withProtocol("sqs")
				.withTopicArn(topicArn);

		// TODO: Submit the request using the subscribe method of the snsClient object.
		snsClient.subscribe(subscribeRequest);
	}

	@Override
	public void publishTopicMessage(AmazonSNSClient snsClient, String topicArn, String subject, String message) {
		// TODO: Construct a PublishRequest object using the provided subject, message, and topic ARN.
		PublishRequest publishRequest = new PublishRequest().withMessage(message).withSubject(subject)
				.withTopicArn(topicArn);

		// TODO: Submit the request using the publish method of the snsClient object.
		snsClient.publish(publishRequest);
	}

	@Override
	public void postToQueue(AmazonSQSClient sqsClient, String queueUrl, String messageText) {
		// TODO: Construct a SendMessageRequest object using the provided queue URL and message.
		SendMessageRequest sendMessageRequest = new SendMessageRequest().withMessageBody(messageText).withQueueUrl(
				queueUrl);

		// TODO: Submit the request using the sendMessage method of the sqsClient object.
		sqsClient.sendMessage(sendMessageRequest);
	}

	@Override
	public List<Message> readMessages(AmazonSQSClient sqsClient, String queueUrl) {
		ReceiveMessageResult receiveMessageResult;

		// TODO: Construct a ReceiveMessageRequest object using the queue URL provided. Set the maximum number of
		// messages to 10.
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withMaxNumberOfMessages(10)
				.withQueueUrl(queueUrl);

		// TODO: Submit the request using the receiveMessage method of the sqsClient object. Store the result in the
		// receiveMessageResult object (already defined).
		receiveMessageResult = sqsClient.receiveMessage(receiveMessageRequest);

		return receiveMessageResult.getMessages();
	}

	@Override
	public void removeMessage(AmazonSQSClient sqsClient, String queueUrl, String receiptHandle) {
		// TODO: Construct a DeleteMessageRequest object using the specified queue URL and receipt handle.
		DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest().withQueueUrl(queueUrl)
				.withReceiptHandle(receiptHandle);

		// TODO: Submit the request using the deleteMessage method of the sqsClient object.
		sqsClient.deleteMessage(deleteMessageRequest);
	}

	@Override
	public void deleteSubscriptions(AmazonSNSClient snsClient, String topicArn) {
		// TODO: Construct a ListSubscriptionsByTopicRequest object using the provided topic ARN.
		ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest()
				.withTopicArn(topicArn);

		// TODO: Submit the request using the listSubscriptionsByTopic method of the snsClient object.
		ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = snsClient
				.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);

		// TODO: Iterate over the subscriptions in the request result.
		// TODO: For each subscription, construct an UnsubscribeRequest object using the subscription ARN.
		// TODO: For each unsubscribe request, submit it using the unsubscribe method of the snsClient object.
		for (Subscription subscription : listSubscriptionsByTopicResult.getSubscriptions()) {
			UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest().withSubscriptionArn(subscription
					.getSubscriptionArn());
			snsClient.unsubscribe(unsubscribeRequest);
		}
	}

	@Override
	public void deleteTopic(AmazonSNSClient snsClient, String topicArn) {
		// TODO: Construct a DeleteTopicRequest object using the provided topic ARN.
		DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest().withTopicArn(topicArn);

		// TODO: Submit the request using the deleteTopic method of the snsClient object.
		snsClient.deleteTopic(deleteTopicRequest);
	}

	@Override
	public void deleteQueue(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: Construct a DeleteQueueRequest object using the provided queue URL.
		DeleteQueueRequest deleteQueueRequest = new DeleteQueueRequest().withQueueUrl(queueUrl);

		// TODO: Submit the request using the deleteQueue method of the sqsClient object.
		sqsClient.deleteQueue(deleteQueueRequest);
	}

	@Override
	public void grantNotificationPermission(AmazonSQSClient sqsClient, String queueArn, String queueUrl, String topicArn) {

		Statement statement = new Statement(Effect.Allow).withActions(SQSActions.SendMessage)
				.withPrincipals(new Principal("*")).withConditions(ConditionFactory.newSourceArnCondition(topicArn))
				.withResources(new Resource(queueArn));

		Policy policy = new Policy("SubscriptionPermission").withStatements(statement);

		HashMap<String, String> attributes = new HashMap<String, String>();
		attributes.put("Policy", policy.toJson());

		// Create the request to set the queue attributes for policy
		SetQueueAttributesRequest setQueueAttributesRequest = new SetQueueAttributesRequest().withQueueUrl(queueUrl)
				.withAttributes(attributes);

		// Set the queue policy
		sqsClient.setQueueAttributes(setQueueAttributesRequest);
	}
}
