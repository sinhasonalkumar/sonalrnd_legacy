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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Project: Lab2.1
 */
public abstract class SolutionCode implements ILabCode, IOptionalLabCode {

    @Override
    public void createBucket(AmazonS3 s3Client, String bucketName, Region region) {
    	// Construct a CreateBucketRequest object that contains the provided bucket name.
		// If the region is other than us-east-1, we need to specify a regional constraint.
    	CreateBucketRequest createBucketRequest;
		if (region.getName().equals("us-east-1")) {
			createBucketRequest = new CreateBucketRequest(bucketName);
		}
		else {
			createBucketRequest = new CreateBucketRequest(bucketName, com.amazonaws.services.s3.model.Region.fromValue(region.getName()));
		}

        // Submit the request using the createBucket method of the s3Client object.
        s3Client.createBucket(createBucketRequest);
        
    }

    @Override
    public void putObject(AmazonS3 s3Client, String bucketName, String sourceFileName, String objectKey) {
        File sourceFile = new File(sourceFileName);

        // Construct a PutObjectRequest object using the values provided in the method parameters 
    	PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, sourceFile);
    	
        // Upload the object by submitting the request using the putObject method of the s3Client object.
        s3Client.putObject(putObjectRequest);
    }

    @Override
    public void listObjects(AmazonS3 s3Client, String bucketName) {
        // Construct a ListObjectsRequest object using the provided bucket name.
    	ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);

        // Submit the request using the listObjects method of the s3Client object.
        ObjectListing objectListing = s3Client.listObjects(listObjectsRequest);

        // Take the results and write the object keys and sizes to the console. 
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
        	System.out.println(objectSummary.getKey() + " (size: " + objectSummary.getSize() + ")");
        }
    }

    @Override
    public void makeObjectPublic(AmazonS3 s3Client, String bucketName, String key) {
        // Use the setObjectAcl method of the s3Client object to set the ACL for the specified 
    	// object to CannedAccessControlList.PublicRead.	
        s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
    }

    @Override
    public String generatePreSignedUrl(AmazonS3 s3Client, String bucketName, String key) {
    	Date nowPlusOneHour = new Date(System.currentTimeMillis() + 3600000L);
    	
    	// Construct a GeneratePresignedUrlRequest object for the provided object.
    	GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
    	// Set the expiration value in the request to the nowPlusOneHour object 
    	// (this specifies a time one hour from now). 
    	generatePresignedUrlRequest.setExpiration(nowPlusOneHour);
    	
        // Submit the request using the generatePresignedUrl method of the s3Client object.
    	URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    	// Return the URL as a string.
        return url.toString();
    }
    
    @Override
    public void deleteBucket(AmazonS3 s3Client, String bucketName) {
    	// First, try to delete the bucket.
    	DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucketName);
    	
    	try {
    	    s3Client.deleteBucket(deleteBucketRequest);
    	    // If we got here, no error was generated so we'll assume the bucket was deleted and return.
    	    return;
    	}
    	catch (AmazonS3Exception ex) {
    		if (!ex.getErrorCode().equals("BucketNotEmpty")) {
    			// The only other exception we're going to handle is BucketNotEmpty, so rethrow anything else.
    			throw ex; 
    	    }
    	}
    	
    	// If we got here, the bucket isn't empty, so delete the contents and try again.
    	List<KeyVersion> keys = new ArrayList<KeyVersion>();
    	for (S3ObjectSummary obj : s3Client.listObjects(bucketName).getObjectSummaries()) {
    	    // Add the keys to our list of object.
    	    keys.add(new KeyVersion(obj.getKey()));
    	}
    	// Create the request to delete the objects.
    	DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
    	deleteObjectsRequest.withKeys(keys);
    	// Submit the delete objects request.
    	s3Client.deleteObjects(deleteObjectsRequest);
    	
    	// The bucket is empty now, so attempt the delete again.
    	s3Client.deleteBucket(deleteBucketRequest);
    }
}
