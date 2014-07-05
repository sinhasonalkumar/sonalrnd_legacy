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
package awslabs.lab22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

/**
 * Project: Lab2.2
 */
public abstract class SolutionCode implements ILabCode, IOptionalLabCode {
	@Override
	public void createAccountItem(AmazonDynamoDBClient ddbClient, String tableName, Account account) {
		// Create a HashMap<String, AttributeValue> object to hold the attributes of the item to add.
		Map<String, AttributeValue> itemAttributes = new HashMap<String, AttributeValue>();
		// Add the required items (Company and Email) from the account parameter to the attribute HashMap.
		itemAttributes.put("Company", new AttributeValue().withS(account.getCompany()));
		itemAttributes.put("Email", new AttributeValue().withS(account.getEmail()));

		// Check the account parameter and add all values that aren't empty strings ("") to the attribute HashMap.
		if (!account.getFirst().equals("")) {
			itemAttributes.put("First", new AttributeValue().withS(account.getFirst()));
		}
		if (!account.getLast().equals("")) {
			itemAttributes.put("Last", new AttributeValue().withS(account.getLast()));
		}
		if (!account.getAge().equals("")) {
			itemAttributes.put("Age", new AttributeValue().withN(account.getAge()));
		}

		// Construct a PutItemRequest object to put the attributes into the specified table.
		PutItemRequest putItemRequest = new PutItemRequest().withTableName(tableName).withItem(itemAttributes);

		// Submit the request using the putItem method of the ddbClient object.
		ddbClient.putItem(putItemRequest);
	}

	@Override
	public QueryResult lookupByHashKey(AmazonDynamoDBClient ddbClient, String tableName, String company) {
		// Construct an AttributeValue object containing the provided company name.
		AttributeValue attributeValue = new AttributeValue().withS(company);
		// Construct a Condition object containing the desired comparison ("EQ") and the attribute value
		// containing the company name.
		Condition condition = new Condition().withComparisonOperator("EQ").withAttributeValueList(attributeValue);

		// Construct a QueryRequest object that performs a consistent read on the specified table for the
		// previously constructed condition.
		QueryRequest queryRequest = new QueryRequest().withTableName(tableName).withConsistentRead(true);
		queryRequest.addKeyConditionsEntry("Company", condition);

		// Submit the query by calling the query method of the ddbClient object and return the result.
		return ddbClient.query(queryRequest);
	}

	@Override
	public void updateIfMatch(AmazonDynamoDBClient ddbClient, String tableName, String email, String company,
			String firstNameTarget, String firstNameMatch) {
		// Construct an UpdateItemRequest object for the specified table.
		UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName);

		// Add KeyEntry elements to the request containing AttributeValue objects for the company name and email
		// address provided.
		updateItemRequest.addKeyEntry("Company", new AttributeValue().withS(company));
		updateItemRequest.addKeyEntry("Email", new AttributeValue().withS(email));

		// Add an ExpectedEntry element to the request containing an ExpectedAttributeValue object that contains
		// the value in the firstNameMatch parameter.
		updateItemRequest.addExpectedEntry("First",
				new ExpectedAttributeValue().withValue(new AttributeValue().withS(firstNameMatch)));

		// Add an AttributeUpdatesEntry element to the request containing an AttributeValueUpdate object that
		// contains the value in the firstNameTarget parameter
		updateItemRequest.addAttributeUpdatesEntry("First",
				new AttributeValueUpdate().withAction("PUT").withValue(new AttributeValue().withS(firstNameTarget)));

		// Submit the request using the updateItem method of the ddbClient object.
		ddbClient.updateItem(updateItemRequest);
	}

	@Override
	public void deleteTable(AmazonDynamoDBClient ddbClient, String tableName) {
		String tableStatus = getTableStatus(ddbClient, tableName);
		if (tableStatus.equals("ACTIVE")) {
			System.out.println("Deleting pre-existing table.");
			DeleteTableRequest deleteTableRequest = new DeleteTableRequest().withTableName(tableName);
			ddbClient.deleteTable(deleteTableRequest);
			waitForStatus(ddbClient, tableName, "NOTFOUND");

			System.out.println("Table deletion confirmed.");
		} else if (tableStatus.equals("NOTFOUND")) {
			System.out.println("Skipped deletion operation. Table not found.");
		} else {
			System.out.println("Skipped deletion operation. Table not in correct state.");
		}
	}

	@Override
	public void buildTable(AmazonDynamoDBClient ddbClient, String tableName) {
		System.out.println("Creating table.");
		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName);
		createTableRequest.setAttributeDefinitions(new ArrayList<AttributeDefinition>());
		// Define attributes
		createTableRequest.getAttributeDefinitions().add(
				new AttributeDefinition().withAttributeName("Company").withAttributeType("S"));
		createTableRequest.getAttributeDefinitions().add(
				new AttributeDefinition().withAttributeName("Email").withAttributeType("S"));
		// Define key schema
		createTableRequest.setKeySchema(new ArrayList<KeySchemaElement>());
		createTableRequest.getKeySchema().add(new KeySchemaElement().withAttributeName("Company").withKeyType("HASH"));
		createTableRequest.getKeySchema().add(new KeySchemaElement().withAttributeName("Email").withKeyType("RANGE"));
		// Define provisioned throughput
		createTableRequest.setProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(10L)
				.withWriteCapacityUnits(5L));

		// Submit create request
		ddbClient.createTable(createTableRequest);
		// Pause until the table is active
		waitForStatus(ddbClient, tableName, "ACTIVE");
		System.out.println("Table created and active.");
	}

	@Override
	public String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName) {
		TableDescription tableDescription = getTableDescription(ddbClient, tableName);
		if (tableDescription == null) {
			return "NOTFOUND";
		}
		return tableDescription.getTableStatus();
	}

	@Override
	public TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName) {
		try {
			DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);

			DescribeTableResult describeTableResult = ddbClient.describeTable(describeTableRequest);

			return describeTableResult.getTable();
		} catch (AmazonServiceException ase) {
			// If the table isn't found, there's no problem.
			// If the error is something else, re-throw the exception to bubble it up to the caller.
			if (!ase.getErrorCode().equals("ResourceNotFoundException")) {
				throw ase;
			}
			return null;
		}
	}

	@Override
	public void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status) {
		while (!getTableStatus(ddbClient, tableName).equals(status)) {
			// Sleep for one second.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Just gobble up the exception.
			}
		}
	}

}
