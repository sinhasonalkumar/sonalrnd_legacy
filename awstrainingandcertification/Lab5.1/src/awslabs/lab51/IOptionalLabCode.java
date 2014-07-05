/** 
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not 
 * use this file except in compliance with the License. A copy of the License 
 * is located at
 * 
 * 	http://aws.amazon.com/apache2.0/
 * 
 * or in the "LICENSE" file accompanying this file. This file is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package awslabs.lab51;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Project: Lab5.1
 */
public interface IOptionalLabCode {
    Boolean isImageInDynamo(AmazonDynamoDBClient dynamoDbClient, String tableName, String key);
    Boolean validateSchema(TableDescription tableDescription);
    TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName);
    String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName);
    void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status);
    void deleteTable(AmazonDynamoDBClient ddbClient, String tableName);

    void addImage(AmazonDynamoDBClient dynamoDbClient, String tableName, AmazonS3Client s3Client, String bucketName,
        String imageKey, String filePath);

    void buildTable(AmazonDynamoDBClient ddbClient, String tableName);
}
