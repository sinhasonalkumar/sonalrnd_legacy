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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import awslabs.labutility.LabUtility;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

/**
 * Project: Lab2.2
 */
public class Lab22 {

	private static Region region = Region.getRegion(Regions.US_EAST_1);

	// BEGIN NON-STUDENT_CODE
	private static ILabCode labCode = new StudentCode();
	private static IOptionalLabCode optionalLabCode = new StudentCode();
	private static String tableName = "Accounts";

	/**
	 * Controls the flow of the lab code execution.
	 */
	public static void main(String[] args) {
		try {
			// Create DynamoDB client and set the region to US East (Virginia)
			AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(new ClasspathPropertiesFileCredentialsProvider());
			ddbClient.setRegion(region);

			List<Account> accounts = new ArrayList<Account>();
			accounts.add(new Account().withCompany("Amazon.com").withEmail("johndoe@amazon.com").withFirst("John")
					.withLast("Doe").withAge("33"));
			accounts.add(new Account().withCompany("Asperatus Tech").withEmail("janedoe@amazon.com").withFirst("Jane")
					.withLast("Doe").withAge("24"));
			accounts.add(new Account().withCompany("Amazon.com").withEmail("jimjohnson@amazon.com").withFirst("Jim")
					.withLast("Johnson"));

			// Verify that the table schema is as we expect, and correct any
			// problems we find.
			if (!confirmTableSchema(ddbClient, tableName)) {
				System.out.print("Deleting. ");
				optionalLabCode.deleteTable(ddbClient, tableName);
				System.out.print("Rebuilding. ");
				optionalLabCode.buildTable(ddbClient, tableName);
				System.out.println("Done.");
			}

			System.out.println("Adding items to table.");
			// Create the accounts
			for (Account account : accounts) {
				labCode.createAccountItem(ddbClient, tableName, account);
				System.out.println("Added item: " + account.getCompany() + "/" + account.getEmail());
			}

			System.out.println("Requesting matches for Company == Amazon.com");
			QueryResult queryResult = labCode.lookupByHashKey(ddbClient, tableName, "Amazon.com");
			if (queryResult != null && queryResult.getCount() > 0) {
				// Record was found
				for (Map<String, AttributeValue> item : queryResult.getItems()) {
					System.out.println("Item Found-");

					for (Entry<String, AttributeValue> attribute : item.entrySet()) {
						System.out.print("    " + attribute.getKey() + ":");
						if (attribute.getKey().equals("Age")) {
							System.out.println(attribute.getValue().getN());
						} else {
							System.out.println(attribute.getValue().getS());
						}
					}
				}
			} else {
				System.out.println("No matches found.");
			}

			// Conditionally update a record
			System.out.print("Attempting update. ");
			labCode.updateIfMatch(ddbClient, tableName, "jimjohnson@amazon.com", "Amazon.com", "James", "Jim");
			System.out.println("Done.");
		} catch (Exception ex) {
			LabUtility.dumpError(ex);
		}
	}

	/**
	 * Validate the table matches the expected schema for the lab. Since it's built by hand, there's a decent chance
	 * that it may not be what's expected.
	 * 
	 * @param ddbClient The DynamoDB client object.
	 * @param tableName The name of the table to validate.
	 * 
	 * @return True if the schema of the table is correct. False if a problem is found.
	 */
	private static Boolean confirmTableSchema(AmazonDynamoDBClient ddbClient, String tableName) {
		System.out.println("Confirming table schema.");
		TableDescription tableDescription = optionalLabCode.getTableDescription(ddbClient, tableName);
		if (tableDescription == null) {
			System.out.println("Table does not exist.");
			// Can't match the schema if the table isn't there.
			return false;
		}
		if (!tableDescription.getTableStatus().equals("ACTIVE")) {
			System.out.println("Table is not active.");
			return false;
		}

		if (tableDescription.getAttributeDefinitions() == null || tableDescription.getKeySchema() == null) {
			System.out.println("Schema doesn't match.");
			return false;
		}
		for (AttributeDefinition attributeDefinition : tableDescription.getAttributeDefinitions()) {
			String attributeName = attributeDefinition.getAttributeName();
			if (attributeName.equals("Company") || attributeName.equals("Email") || attributeName.equals("First")
					|| attributeName.equals("Last")) {
				if (!attributeDefinition.getAttributeType().equals("S")) {
					// We have a matching attribute, but the type is wrong.
					System.out.println(attributeDefinition.getAttributeName()
							+ " attribute is wrong type in attribute definition.");
					return false;
				}
			} else if (attributeName.equals("Age")) {
				if (!attributeDefinition.getAttributeType().equals("N")) {
					System.out.println("Age attribute is wrong type in attribute definition.");
					// We have a matching attribute, but the type is wrong.
					return false;
				}
			}
		}
		// If we've gotten here, the attributes are good. Now check the key
		// schema.
		if (tableDescription.getKeySchema().size() != 2) {
			System.out.println("Wrong number of elements in the key schema.");
			return false;
		}
		for (KeySchemaElement keySchemaElement : tableDescription.getKeySchema()) {
			String attributeName = keySchemaElement.getAttributeName();
			if (attributeName.equals("Company")) {
				if (!keySchemaElement.getKeyType().equals("HASH")) {
					// We have a matching attribute, but the type is wrong.
					System.out.println("Company attribute is wrong type in key schema.");
					return false;
				}
			} else if (attributeName.equals("Email")) {
				if (!keySchemaElement.getKeyType().equals("RANGE")) {
					// We have a matching attribute, but the type is wrong.
					System.out.println("Email attribute is wrong type in key schema.");
					return false;
				}
			} else {
				System.out.println("Unexpected attribute (" + keySchemaElement.getAttributeName()
						+ ") in the key schema.");
			}
		}
		System.out.println("Table schema is as expected.");
		// We've passed our checks.
		return true;

	}
	// END NON-STUDENT CODE
}
