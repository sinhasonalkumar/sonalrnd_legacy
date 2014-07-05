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

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

/**
 * Project: Lab2.2
 */

public class StudentCode extends SolutionCode {

	/**
	 * Create a DynamoDB item from the values specified in the account parameter. The names of the attributes in the
	 * item should match the corresponding property names in the Account object. Don't add attributes for fields in the
	 * Account object that are empty.
	 * 
	 * Since the Company and Email attributes are part of the table key, those will always be provided in the Account
	 * object when this method is called. This method will be called multiple times by the code controlling the lab.
	 * 
	 * Important: Even thought the Account.Age property is passed to you as a string, add it to the item as a numerical
	 * value.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table to add the item to.
	 * @param account The Account object containing the data to add.
	 */
	@Override
	public void createAccountItem(AmazonDynamoDBClient ddbClient, String tableName, Account account) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		super.createAccountItem(ddbClient, tableName, account);
	}

	/**
	 * Construct a query using the criteria specified and return the result object. Hint: Use the query() method of the
	 * client object.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table to query.
	 * @param company The company name to search for.
	 * @return The QueryResult object containing the results of the request.
	 */
	@Override
	public QueryResult lookupByHashKey(AmazonDynamoDBClient ddbClient, String tableName, String company) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		return super.lookupByHashKey(ddbClient, tableName, company);
	}

	/**
	 * Find items in the table matching the company and email parameter values. Set the value for the First attribute to
	 * the firstNameTarget parameter value only if the attribute value matches the firstNameMatch parameter value. Hint:
	 * This can be accomplished with a single request using the updateItem() method of the client object.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table containing the items.
	 * @param email The value to match against the Email attribute.
	 * @param company The value to match against the Company attribute.
	 * @param firstNameTarget The new value for the First attribute to use if a match is found.
	 * @param firstNameMatch The value for the First attribute to match.
	 */
	@Override
	public void updateIfMatch(AmazonDynamoDBClient ddbClient, String tableName, String email, String company,
			String firstNameTarget, String firstNameMatch) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		super.updateIfMatch(ddbClient, tableName, email, company, firstNameTarget, firstNameMatch);
	}

	// BEGIN OPTIONAL TASKS
	/**
	 * Request the table description for the specified table and return it to the caller. Hint: Use the describeTable()
	 * method of the client object.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table.
	 * @return The TableDescription object for the table. Null if the table wasn't found.
	 */
	@Override
	public TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		return super.getTableDescription(ddbClient, tableName);
	}

	/**
	 * Return the table status string that is associated with the specified table. The table status is a property of the
	 * TableDescription object.
	 * 
	 * Hint: Call your getTableDescription() method to get the TableDescription object. If the method returns Null, then
	 * return "NOTFOUND" from this method.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table.
	 * @return The table status string. "NOTFOUND" if the table doesn't exist or can't be located.
	 */
	@Override
	public String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		return super.getTableStatus(ddbClient, tableName);
	}

	/**
	 * Pause execution on this thread until the table status matches the provided status string. Hint: You will need to
	 * request the status repeatedly and put the thread to sleep between requests. You can decided how long to pause,
	 * but for this lab, it shouldn't be less than one second.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table to inspect.
	 * @param status The desired status.
	 */
	@Override
	public void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		super.waitForStatus(ddbClient, tableName, status);
	}

	/**
	 * Create the table that is used in this lab. Don't return from this method until the table state is "ACTIVE". Hint:
	 * Call the waitForStatus() method that you implemented above in order to wait. Build the table to match these
	 * parameters: -- Attributes - "Company" a string, and "Email" also a string -- Hash Key Attribute - "Company" --
	 * Range Key Attribute - "Email" -- Provisioned Capacity - 5 reads/5 writes
	 * 
	 * This method will be called by the lab controller code if it determines that the table needs to be rebuilt (ex.
	 * the schema doesn't match our expectations).
	 * 
	 * In order to complete this task, you will need to investigate how to create a table in DynamoDB because it is not
	 * covered in the course materials.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table to create.
	 */
	@Override
	public void buildTable(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		super.buildTable(ddbClient, tableName);
	}

	/**
	 * Delete the specified table. This method will be called if the lab controller code determines that the existing
	 * table is invalid for the lab.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table.
	 */
	@Override
	public void deleteTable(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: Replace this call to the super class with your own implementation of the method.
		super.deleteTable(ddbClient, tableName);
	}

	// END OPTIONAL TASKS
}
