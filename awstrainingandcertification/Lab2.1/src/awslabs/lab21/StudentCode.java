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
package awslabs.lab21;

import java.io.File;
import java.net.URL;
import java.util.Date;

import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Project: Lab2.1
 * 
 * The primary purpose of this lab is to gain experience working with S3 programmatically.
 */
@SuppressWarnings("unused")
public class StudentCode extends SolutionCode {
    /**
     * Use the provided S3 client object to create the specified bucket.
     * Hint: Use the createBucket() method of the client object.
     * 		 If the region is anything other than us-east-1, it needs to be 
     * 		 explicitly specified in the request.
     * 
     * @param s3Client	 The S3 client object.	
     * @param bucketName The name of the bucket to create.
     */
    @Override
    public void createBucket(AmazonS3 s3Client, String bucketName, Region region) {
	//TODO: Replace this call to the super class with your own implementation of the method.
	super.createBucket(s3Client, bucketName, region);
    }

    /**
     * Upload the provided item to the specified bucket.
     * Hint: Use the putObject() method of the client object.
     * 
     * @param s3Client	 The S3 client object.	
     * @param bucketName The name of the target bucket.
     * @param sourceFile The name of the file to upload.
     * @param objectKey  The key to assign to the new S3 object.
     */
    @Override
    public void putObject(AmazonS3 s3Client, String bucketName, String sourceFile, String objectKey) {
	//TODO: Replace this call to the super class with your own implementation of the method.
	super.putObject(s3Client, bucketName, sourceFile, objectKey);
    }

    /**
     * List the contents of the specified bucket by writing the object key and item size to the console.
     * Hint: Use the listObjects() method of the client object.
     * 
     * @param s3Client	 The S3 client object.	
     * @param bucketName The name of the bucket containing the objects to list.
     */
    @Override
    public void listObjects(AmazonS3 s3Client, String bucketName) {
	//TODO: Replace this call to the super class with your own implementation of the method.
	super.listObjects(s3Client, bucketName);
    }

    /**
     * Change the ACL for the specified object to make it publicly readable.
     * Hint: Call the setObjectAcl() method of the client object. Use the CannedAccessControlList 
     * enumeration to set the ACL for the object to PublicRead.
     * 
     * @param s3Client	 The S3 client object.	
     * @param bucketName The name of the bucket containing the object.
     * @param key        The key used to identify the object.
     */
    @Override
    public void makeObjectPublic(AmazonS3 s3Client, String bucketName, String key) {
	//TODO: Replace this call to the super class with your own implementation of the method.
	super.makeObjectPublic(s3Client, bucketName, key);
    }

    /**
     * Create and return a pre-signed URL for the specified item. Set the URL to expire one hour from the 
     * moment it was generated.
     * Hint: Use the generatePresignedUrl() method of the client object.
     * 
     * @param s3Client	 The S3 client object.	
     * @param bucketName The name of the bucket containing the object.
     * @param key	 The key used to identify the object.
     * @return 		 The pre-signed URL for the object.
     */
    @Override
    public String generatePreSignedUrl(AmazonS3 s3Client, String bucketName, String key) {
	//TODO: Replace this call to the super class with your own implementation of the method.
	return super.generatePreSignedUrl(s3Client, bucketName, key);
    }

    /**
     * Delete the specified bucket. You will use the deleteBucket() method of the client object to 
     * delete the bucket, but first you will need to delete the bucket contents. To delete the contents, 
     * you will need to list the objects and delete them individually (DeleteObject() method) or as a 
     * batch (DeleteObjects() method).
     * 
     * The purpose of this task is to gain experience writing applications that remove unused AWS resources
     * in an automated manner.
     * 
     * @param s3Client	 The S3 client object.	
     * @param bucketName The name of the bucket to delete.
     */
    @Override
    public void deleteBucket(AmazonS3 s3Client, String bucketName) {
	//TODO: Replace this call to the super class with your own implementation of the method.
	super.deleteBucket(s3Client, bucketName);
    }
}
