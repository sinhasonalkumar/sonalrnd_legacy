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
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Project: Lab5.1
 */
public class StudentCode extends SolutionCode {
	public StudentCode(Lab51 lab) {
		super(lab);
	}

	/**
	 * getUrlForItem - Use the provided S3 client to generate a pre-signed URL for the item identified by the
	 * specified bucket and key. Set the link to expire in 1 minute.
	 * 
	 * @param s3Client	The S3 client object.
	 * @param key		The key of the object to provide a link for.
	 * @param bucket	The bucket containing the object.
	 * @return A pre-signed URL for the object.
	 */
	@Override
	public String getUrlForItem(AmazonS3Client s3Client, String key, String bucket) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.getUrlForItem(s3Client, key, bucket);
	}

	/**
	 * getImageItems - Return a collection of items from DynamoDB containing the details for the images to display
	 * on the page. The name of the table containing the items is identified by the value of SESSIONTABLE. 
	 * Filter the results based on the key prefix defined in PARAM3. You should identify the items using the 
	 * scan operation. The items collection is in the result object.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @return The collection of matching items.
	 */
	@Override
	public List<Map<String, AttributeValue>> getImageItems(AmazonDynamoDBClient dynamoDbClient) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.getImageItems(dynamoDbClient);
	}

	/**
	 * createS3Client - Construct and return an S3 client object that applies the region constraint identified in the
	 * REGION setting.
	 * 
	 * @param credentials	The credentials to use for the client object.
	 * @return The client object.
	 */
	@Override
	public AmazonS3Client createS3Client(AWSCredentials credentials) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.createS3Client(credentials);
	}

	/**
	 * createDynamoDbClient - Construct and return a DynamoDB client object that applies the region constraint 
	 * identified in the REGION setting.
	 * 
	 * @param credentials 	The credentials to use for the client object.
	 * @return The client object.
	 */
	@Override
	public AmazonDynamoDBClient createDynamoDbClient(AWSCredentials credentials) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.createDynamoDbClient(credentials);
	}

	/**
	 * addItemsToPage - This method is used to convert the items in the DynamoDB items collection to elements that 
	 * can be rendered on a web page. To complete the task:
	 * (1) Loop through the items in the collection and extract the "Key" and "Bucket" attribute values.
	 * (2) Use the key and bucket values to generate a pre-signed URL for each object represented. To generate
	 *     the URL, call your implementation of the GetUrlForItem() method and capture the return value.
	 * (3) For each item, call the _Default.AddImageToPage() method, passing in the key, bucket, and URL values
	 *     as method parameters.
	 * 
	 * @param s3Client	The S3 client object.
	 * @param items		The collection of items to add to the page.
	 */
	@Override
	public void addItemsToPage(AmazonS3Client s3Client, List<Map<String, AttributeValue>> items) {
		//TODO: Replace this call to the super class with your own method implementation.
		super.addItemsToPage(s3Client, items);
	}

	/**
	 * isImageInDynamo - Inspect the DynamoDB table and determine if it contains an item matching the specified
	 * hash key.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table to search.
	 * @param key				The key of the item to locate.
	 * @return True if the item exists, false if it doesn't. 
	 */
	@Override
	public Boolean isImageInDynamo(AmazonDynamoDBClient dynamoDbClient, String tableName, String key) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.isImageInDynamo(dynamoDbClient, tableName, key);
	}

	/**
	 * getTableDescription - Request the table description for the specified table and return it to the caller.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table.
	 * @return The table description object. Null if the table wasn't found.
	 */
	@Override
	public TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.getTableDescription(ddbClient, tableName);
	}

	/**
	 * validateSchema - Validate the schema described by the tableDescription parameter. We expect the table to have
	 * the following characteristics:
	 *   Schema - At least two attributes, "Key" and "Bucket" both of string types.
	 *   Hash Key - Single attribute named "Key" of type string.
	 *   Range Key - Single attribute named "Bucket" of type string.
	 * 
	 * @param tableDescription	The table definition.
	 * @return True if the schema matches what we expect, false if the schema was invalid or an exception was thrown.
	 */
	@Override
	public Boolean validateSchema(TableDescription tableDescription) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.validateSchema(tableDescription);
	}
	
	/**
	 * getTableStatus - Return the table status string that is associated with the specified table. 
	 * The table status is a property of the TableDescription object.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table to inspect.
	 * @return The table status string. "NOTFOUND" if the table doesn't exist or can't be located.
	 */
	@Override
	public String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: Replace this call to the super class with your own method implementation.
		return super.getTableStatus(ddbClient, tableName);
	}

	/**
	 * waitForStatus - Pause execution on this thread until the table status matches the provided status string.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table.
	 * @param status			The desired table status.
	 */
	@Override
	public void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status) {
		//TODO: Replace this call to the super class with your own method implementation.
		super.waitForStatus(ddbClient, tableName, status);
	}

	/**
	 * deleteTable - Delete the specified table. This method will be called if the lab controller code determines 
	 * that the existing table is invalid for the lab.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table to delete.
	 */
	@Override
	public void deleteTable(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: Replace this call to the super class with your own method implementation.
		super.deleteTable(ddbClient, tableName);
	}

	/**
	 * addImage - Upload the specified image to S3 and add a reference to the image to DynamoDB. 
	 * The DynamoDB item representing the image should contain two attributes:
	 *   Key - The key to the object in S3
	 *   Bucket - The bucket that the object is located in
	 *   
	 * Do not set any permissions for the object in S3; keep the restrictive defaults.
	 * This method will be called if the lab controller code determines that the images used in the lab aren't
	 * in place or referenced properly in DynamoDB. It will be executed at least once.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table to put the items into.
	 * @param s3Client			The S3 client object.
	 * @param bucketName		The name of the bucket to use for the objects in S3. 
	 * @param imageKey			The key to use for the object in S3. 
	 * @param filePath			The path of the image to be uploaded.
	 */
	@Override
	public void addImage(AmazonDynamoDBClient dynamoDbClient, String tableName, AmazonS3Client s3Client,
			String bucketName, String imageKey, String filePath) {
		//TODO: Replace this call to the super class with your own method implementation.
		super.addImage(dynamoDbClient, tableName, s3Client, bucketName, imageKey, filePath);
	}

	/**
	 * buildTable - Create the table that is used in this lab. Don't return from this method until the table state
	 * is "ACTIVE". 
	 * 
	 * Build the table to match these parameters:
	 *   Attributes - "Key" a string, and "Bucket" also a string
	 *   Hash Key Attribute - "Key"
	 *   Range Key Attribute - "Bucket"
	 *   Provisioned Capacity - 5 Reads/5 Writes
	 *   
	 * This method will be called by the lab controller code at least once in order to prepare the lab. It will
	 * also be called if the lab controller code determines that the table needs to be rebuilt (ex. the schema
	 * doesn't match our expectations.
	 * 
	 * @param dynamoDbClient	The DynamoDB client object.
	 * @param tableName			The name of the table to create.
	 */
	@Override
	public void buildTable(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: Replace this call to the super class with your own method implementation.
		super.buildTable(ddbClient, tableName);
	}

}
