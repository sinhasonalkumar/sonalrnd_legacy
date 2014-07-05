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
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;

/**
 * Project: Lab4.1
 */
public class StudentCode extends SolutionCode {
	/**
	 * Find and return the ARN for the specified user.
	 * Hint: Use the getUser() method of the client object. The ARN for the user is in the response.
	 * 
	 * @param iamClient	The IAM client object.
	 * @param userName	The name of the user to find.
	 * @return The ARN of the specified user.
	 */
	@Override
	public String prepMode_GetUserArn(AmazonIdentityManagementClient iamClient, String userName) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		return super.prepMode_GetUserArn(iamClient, userName);
	}

	/**
	 * Create the specified role using the specified policy and trust relationship text. Return the role ARN.
	 * 
	 * @param iamClient				The IAM client object.
	 * @param roleName				The name of the role to create.
	 * @param policyText			The policy to attach to the role.
	 * @param trustRelationshipText	The policy defining who can assume the role.
	 * @return The ARN for the newly created role.
	 */
	@Override
	public String prepMode_CreateRole(AmazonIdentityManagementClient iamClient, String roleName, String policyText,
			String trustRelationshipText) {

		//TODO: Replace this call to the super class with your own method implementation.
		return super.prepMode_CreateRole(iamClient, roleName, policyText, trustRelationshipText);
	}

	/**
	 * Assume the specified role.
	 * Hint: Use the assumeRole() method of the client object.
	 * Optional: You may see an eventual consistency issue here. The AssumeRole permissions may not
	 * have propagated through the system yet which could prevent us from assuming the role. Check for
	 * an AmazonServiceException with an ErrorCode of "AccessDenied" and retry the assume role operation
	 * after a short wait (with exponential back-off on retries). If you decide to stop retrying,
	 * return null.
	 * 	
	 * @param stsClient			The STS client object.
	 * @param roleArn			The ARN of the role to assume.
	 * @param roleSessionName	The name to use as the role session name.
	 * @return The role credentials, or null if there was a problem.
	 */
	@Override
	public Credentials appMode_AssumeRole(AWSSecurityTokenServiceClient stsClient, String roleArn,
			String roleSessionName) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		return super.appMode_AssumeRole(stsClient, roleArn, roleSessionName);
	}

	/**
	 * Create session/temporary credentials using the provided credentials (previously returned from the assumeRole()
	 * method call), and use the session credentials to create an S3 client object.
	 * 
	 * @param credentials	The credentials to use for creating session credentials.
	 * @param region		The region endpoint to use for the client.
	 * @return The S3 client object.
	 */
	@Override
	public AmazonS3Client appMode_CreateS3Client(Credentials credentials, Region region) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		return super.appMode_CreateS3Client(credentials, region);
	}

	/**
	 * Remove any roles that match the names of the roles we'll be creating. This will be called 
	 * by the lab controller code to clean up resources that might conflict with proper lab execution.
	 * 
	 * @param iamClient	The IAM client object.
	 * @param roles		The list of role names.
	 */
	@Override
	public void prepMode_RemoveRoles(AmazonIdentityManagementClient iamClient, String... roles) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		super.prepMode_RemoveRoles(iamClient, roles);
	}

	/**
	 * Create a bucket that will be used later in the lab. This is housekeeping code that is used to prepare the 
	 * environment for the lab exercise.
	 * 
	 * @param s3Client		The S3 client object.
	 * @param bucketName	The bucket to create.
	 */
	@Override
	public void prepMode_CreateBucket(AmazonS3Client s3Client, String bucketName, Region region) {

		//TODO: Replace this call to the super class with your own method implementation.
		super.prepMode_CreateBucket(s3Client, bucketName, region);
	}

	/**
	 * Test access to the SNS service using the provided credentials by requesting a listing of the SNS topics.
	 * You are free to test in any way you like. Submit any sort of request and watch for an exception.
	 * 
	 * @param region		The region endpoint to use for the client connection.
	 * @param credentials	The credentials to use.
	 * @return True, if the service is accessible. False, if the credentials are rejected. 
	 */
	@Override
	public Boolean appMode_TestSnsAccess(Region region, BasicSessionCredentials credentials) {

		//TODO: Replace this call to the super class with your own method implementation.
		return super.appMode_TestSnsAccess(region, credentials);
	}

	/**
	 * Test access to the SQS service using the provided credentials by requesting a listing of the SQS queues.
	 * You are free to test in any way you like. Submit any sort of request and watch for an exception.
	 * 
	 * @param region		The region endpoint to use for the client connection.
	 * @param credentials	The credentials to use.
	 * @return True, if the service is accessible. False, if the credentials are rejected. 
	 */
	@Override
	public Boolean appMode_TestSqsAccess(Region region, BasicSessionCredentials credentials) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		return super.appMode_TestSqsAccess(region, credentials);
	}

	/**
	 * Test access to the IAM service using the provided credentials by requesting a listing of the IAM users.
	 * You are free to test in any way you like. Submit any sort of request and watch for an exception.
	 * 
	 * @param region		The region endpoint to use for the client connection.
	 * @param credentials	The credentials to use.
	 * @return True, if the service is accessible. False, if the credentials are rejected. 
	 */
	@Override
	public Boolean appMode_TestIamAccess(Region region, BasicSessionCredentials credentials) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		return super.appMode_TestIamAccess(region, credentials);
	}

	/**
	 * Cleanup/delete the buckets that were created by the lab.
	 * 
	 * @param s3Client		The S3 client object.
	 * @param bucketNames	The buckets to delete.
	 */
	@Override
	public void removeLabBuckets(AmazonS3Client s3Client, List<String> bucketNames) {
		
		//TODO: Replace this call to the super class with your own method implementation.
		super.removeLabBuckets(s3Client, bucketNames);
	}
}
