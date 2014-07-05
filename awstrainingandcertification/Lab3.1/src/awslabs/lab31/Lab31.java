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

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import awslabs.labutility.LabUtility;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDeletedRecentlyException;

/**
 * Project: Lab3.1
 */
public class Lab31 {

	// TODO: Select the region containing the table that you are using.
	private static Region region = Region.getRegion(Regions.US_EAST_1);

	// BEGIN NON-STUDENT CODE
	private static ILabCode labCode = new StudentCode();
	private static IOptionalLabCode optionalLabCode = new StudentCode();

	public static void main(String[] args) {
		try {
			// Create an SQS client
			AmazonSQSClient sqsClient = new AmazonSQSClient(new ClasspathPropertiesFileCredentialsProvider());
			sqsClient.setRegion(region);

			// Create an SNS client
			AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
			snsClient.setRegion(region);

			String queueName = "Notifications";
			String topicName = "ClassroomEvent";

			// Creating the queue will fail if we've just deleted it and are recreating it
			// which is a possibility if you're tracking down a code error. If that happens,
			// pause and retry for up to a minute.
			System.out.println("Creating " + queueName + " queue.");

			Boolean retry = true, notified = false;
			// Create a timeout for 60-seconds from now.
			Date timeout = new Date(System.currentTimeMillis() + 60000L);
			String queueUrl = "";

			while (retry) {
				try {
					queueUrl = labCode.createQueue(sqsClient, queueName);
					retry = false;
				} catch (QueueDeletedRecentlyException ex) {
					if (new Date().before(timeout)) {
						if (!notified) {
							System.out
									.println("The attempt to recreate the queue failed because the queue was deleted too");
							System.out.println("recently. Waiting and retrying for up to 1 minute.");
							notified = true;
						}
						// Timeout hasn't expired yet, so wait and retry in 5 seconds.
						System.out.print(".");
						Thread.sleep(5000);
					} else {
						System.out.println("Retry timeout expired. Aborting.");
						throw ex;
					}
				}

			}
			if (notified) {
				System.out.println("Recovered.");
			}

			System.out.println("URL for new queue: " + queueUrl);

			// List SQS queues
			System.out.println("Getting ARN for " + queueName + " queue.");
			String queueArn = labCode.getQueueArn(sqsClient, queueUrl);
			System.out.println("ARN for queue: " + queueArn);

			// Create an SNS topic and get ARN
			System.out.println("Creating " + topicName + " topic.");
			String topicArn = labCode.createTopic(snsClient, topicName);
			System.out.println("New topic ARN: " + topicArn);

			System.out.println("Granting the notification topic permission to post in the queue.");
			optionalLabCode.grantNotificationPermission(sqsClient, queueArn, queueUrl, topicArn);
			System.out.println("Permission granted.");

			// Create an SNS subscription
			System.out.println("Creating SNS subscription.");
			labCode.createSubscription(snsClient, queueArn, topicArn);
			System.out.println("Subscription created.");

			// Publish message to topic
			String messageText = "This is the SNS topic notification body.";
			String messageSubject = "SNSTopicNotification";

			System.out.println("Publishing SNS topic notification.");
			labCode.publishTopicMessage(snsClient, topicArn, messageSubject, messageText);
			System.out.println("Notification published.");

			// Send a message to the "Notifications" queue
			messageText = "This is the message posted to the queue directly.";
			System.out.println("Posting message to queue directly.");
			labCode.postToQueue(sqsClient, queueUrl, messageText);
			System.out.println("Message posted.");

			// Read message from queue
			System.out.println("Reading messages from queue.");

			List<Message> messages = labCode.readMessages(sqsClient, queueUrl);
			// We expect two messages here
			if (messages.size() < 2) {
				// Try to read again and see if we've picked up the missing message(s).
				messages.addAll(labCode.readMessages(sqsClient, queueUrl));
				if (messages.size() < 2) {
					System.out.println(">>WARNING<< We didn't receive the expected number of messages. Investigate.");
				} else {
					System.out.println();
					System.out.println("============================================================================");
					System.out.println("PROBLEM: ReadMessages() had to be called twice to collect all the messages.");
					System.out.println("         Did you remember to set the MaxNumberOfMessages property in the ");
					System.out.println("         ReceiveMessageRequest object?");
					System.out.println("============================================================================");
					System.out.println();

				}
			}
			PrintAndRemoveMessagesInResponse(sqsClient, messages, queueUrl);

			// Locate and delete the SNS subscription
			System.out.println("Removing provisioned resources.");
			labCode.deleteSubscriptions(snsClient, topicArn);
			System.out.println("Subscriptions removed.");

			// Delete the SNS Topic
			labCode.deleteTopic(snsClient, topicArn);
			System.out.println("Topic deleted.");
			// Locate the previously created queue and delete
			labCode.deleteQueue(sqsClient, queueUrl);
			System.out.println("Queue deleted.");
		} catch (Exception ex) {
			LabUtility.dumpError(ex);
		}
	}

	// Print the message contents to the console window
	private static void PrintAndRemoveMessagesInResponse(AmazonSQSClient sqsClient, List<Message> messages,
			String queueUrl) {

		for (Message message : messages) {

			System.out.println("\nQueue Message:");

			System.out.println("\tMessageId : " + message.getMessageId());
			System.out.println("\tMD5OfBody : " + message.getMD5OfBody());
			System.out.println("\tBody : " + message.getBody());

			if (message.getAttributes().size() > 0) {
				System.out.println("\tMessage Attributes");

				for (Entry<String, String> entry : message.getAttributes().entrySet()) {
					System.out.println("\t\t" + entry.getKey() + " : " + entry.getValue());
				}
			}

			System.out.println("\nDeleting message.");
			labCode.removeMessage(sqsClient, queueUrl, message.getReceiptHandle());
			System.out.println("Message deleted.");
		}
	}
	// END NON-STUDENT CODE

}
