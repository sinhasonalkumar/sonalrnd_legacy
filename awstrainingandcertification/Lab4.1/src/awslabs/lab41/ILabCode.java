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

import com.amazonaws.regions.Region;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
/**
 * Project: Lab4.1
 */
public interface ILabCode {
    String prepMode_GetUserArn(AmazonIdentityManagementClient iamClient, String userName);

    String prepMode_CreateRole(AmazonIdentityManagementClient iamClient, String roleName, String policyText,
        String trustRelationshipText);

    Credentials appMode_AssumeRole(AWSSecurityTokenServiceClient stsClient, String roleArn,
        String roleSessionName);

    AmazonS3Client appMode_CreateS3Client(Credentials credentials, Region region);

}
