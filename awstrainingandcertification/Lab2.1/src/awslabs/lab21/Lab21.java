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
import java.util.UUID;

import awslabs.labutility.LabUtility;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class Lab21 {

    private static Region region = Region.getRegion(Regions.US_EAST_1);

    // BEGIN NON-STUDENT_CODE
    private static ILabCode labCode = new StudentCode();
    private static IOptionalLabCode optionalLabCode = new StudentCode();
    private static String labBucketPrefix = "awslab";
    
    /**
     * Controls the flow of the lab code execution.
     */
    public static void main(String[] args) {
        try {
            String bucketName = labBucketPrefix + UUID.randomUUID().toString().substring(0, 8);
            String testImage = "test-image.png";
            String publicTestImage = "public-test-image.png";
            String testImage2 = "test-image2.png";

            // Create an S3 client
            AmazonS3Client s3Client = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
            s3Client.setRegion(region);

            // Clean up leftovers from previous instances of this lab
            CleanupPreviousLabRuns(s3Client);
            
            // Create a bucket
            System.out.println("Creating bucket " + bucketName);
            labCode.createBucket(s3Client, bucketName, region);

            // Create an object in the bucket
            File sourceFile = new File(testImage);
            if (sourceFile.exists()) {
                System.out.println("Uploading object: " + testImage);
        	labCode.putObject(s3Client, bucketName, testImage, testImage);
                System.out.println("Uploading complete.");
            }
            else {
            	System.out.println("Source file " + testImage + " doesn't exist.");
            	return;
            }

            sourceFile = new File(testImage2);
            if (sourceFile.exists()) {
                System.out.println("Uploading another copy of the object (will be made publically available later).");
                labCode.putObject(s3Client, bucketName, testImage2, publicTestImage);
                System.out.println("Uploading complete.");
            }
            else {
            	System.out.println("Source file " + testImage2 + " doesn't exist.");
            	return;       	
            }

            // List objects in the bucket
            System.out.println("Listing items in bucket: " + bucketName);
            labCode.listObjects(s3Client, bucketName);
            System.out.println("Listing complete.");

            // Change the ACL on one of the objects to make it public
            System.out.println("Changing the ACL to make an object public");
            labCode.makeObjectPublic(s3Client, bucketName, publicTestImage);
            System.out.println("Done the object should be publically available now. Test this URL to confirm:");
            System.out.println("  http://" + bucketName + ".s3.amazonaws.com/" + publicTestImage);
            
            // Generate a pre-signed URL for an object to grant temporary access to the file.
            System.out.println("Generating presigned URL.");
            String presignedUrl = labCode.generatePreSignedUrl(s3Client, bucketName, testImage);
            System.out.println("Done. Test this URL to confirm:");
            System.out.println("  " + presignedUrl);

        }
        catch (Exception ex) {
            LabUtility.dumpError(ex);
        }

    }
    
    private static void CleanupPreviousLabRuns(AmazonS3 s3Client) {
	Boolean isInformed = false;
	
	// Get list of buckets 
	for (Bucket bucket : s3Client.listBuckets()) {
	    if (bucket.getName().startsWith(labBucketPrefix)) {
		if (!isInformed) {
		    System.out.println("Cleaning up resources from previous lab run.");
		    isInformed = true;
		}
		// Delete the matching buckets 
		System.out.print("Deleting " + bucket.getName() + " bucket. ");
		optionalLabCode.deleteBucket(s3Client, bucket.getName());
		System.out.println("Done.");
	    }
	}
    }
    // END NON-STUDENT_CODE
}
