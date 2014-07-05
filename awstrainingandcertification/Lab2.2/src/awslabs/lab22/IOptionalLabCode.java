/**
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not
 * use this file except in compliance with the License. A copy of the License is
 * located at
 * 
 * http://aws.amazon.com/apache2.0/
 * 
 * or in the "LICENSE" file accompanying this file. This file is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package awslabs.lab22;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

/**
 * Project: Lab2.2
 */
public interface IOptionalLabCode {
	void deleteTable(AmazonDynamoDBClient ddbClient, String tableName);

	void buildTable(AmazonDynamoDBClient ddbClient, String tableName);

	String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName);

	TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName);

	void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status);
}
