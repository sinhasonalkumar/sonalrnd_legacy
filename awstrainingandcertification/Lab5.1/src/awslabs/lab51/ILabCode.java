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
package awslabs.lab51;

import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Project: Lab5.1
 */
public interface ILabCode {
	String getUrlForItem(AmazonS3Client s3Client, String key, String bucket);
	List<Map<String, AttributeValue>> getImageItems(AmazonDynamoDBClient dynamoDbClient);
	AmazonS3Client createS3Client(AWSCredentials credentials);
	AmazonDynamoDBClient createDynamoDbClient(AWSCredentials credentials);
	void addItemsToPage(AmazonS3Client s3Client, List<Map<String, AttributeValue>> images);
}
