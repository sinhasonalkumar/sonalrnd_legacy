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

import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;

public interface ILabCode {
	void createBucket(AmazonS3 s3Client, String bucketName, Region region);
    void putObject(AmazonS3 s3Client, String bucketName, String sourceFileName, String objectKey);
    void listObjects(AmazonS3 s3Client, String bucketName);
    void makeObjectPublic(AmazonS3 s3Client, String bucketName, String key);
    String generatePreSignedUrl(AmazonS3 s3Client, String bucketName, String key);
}
