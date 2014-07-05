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
package awslabs.lab41;

import java.util.List;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Project: Lab4.1
 */
public interface IOptionalLabCode {
    void prepMode_RemoveRoles(AmazonIdentityManagementClient iamClient, String... roles);
	void prepMode_CreateBucket(AmazonS3Client s3Client, String bucketName, Region region);	

    Boolean appMode_TestSnsAccess(Region region, BasicSessionCredentials credentials);
    Boolean appMode_TestSqsAccess(Region region, BasicSessionCredentials credentials);
    Boolean appMode_TestIamAccess(Region region, BasicSessionCredentials credentials);

    void removeLabBuckets(AmazonS3Client s3Client, List<String> bucketNames);
}
