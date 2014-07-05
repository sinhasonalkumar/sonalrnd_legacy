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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.UUID;

import awslabs.labutility.LabUtility;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;

/**
 * Project: Lab4.1
 */
public class Lab41 {

	// TODO: Select the region containing the table that you are using.
	private static Region region = Region.getRegion(Regions.US_EAST_1);

	// BEGIN NON-STUDENT CODE
	private static ILabCode labCode = new StudentCode();
	private static IOptionalLabCode optionalLabCode = new StudentCode();
	
	private String LabUserName="LabAppUser";

	public static void main(String[] args) {
		LabVariables labVariables = null;
		Lab41 lab41 = new Lab41();
		try
		{
			// Start the "prep" mode operations to make sure that the resources are all in the expected state.
			System.out.println("Starting up in 'prep' mode.");
            labVariables = lab41.prepMode_Run();

            System.out.println("\nPrep complete. Transitioning to 'app' mode.");
            lab41.appMode_Run(labVariables);
		}
		catch (Exception ex)
		{
			LabUtility.dumpError(ex);
		}
		finally {
            try
            {
                if (labVariables != null)
                {
                	System.out.println("");
                	System.out.print("Lab run completed. Cleaning up buckets. ");
                	AmazonS3Client s3Client = new AmazonS3Client(lab41.getCredentials("prepmode"));
                	s3Client.setRegion(region);
                	optionalLabCode.removeLabBuckets(s3Client, labVariables.getBucketNames());
                	System.out.println("Done.");
                }
            }
            catch (Exception ex)
            {
                System.out.println("Attempt to clean up buckets failed. " + ex.getMessage());
            }
		}
	}
	
	public LabVariables prepMode_Run() throws IOException {
    	LabVariables labVariables = new LabVariables();
    	
    	AWSCredentials credentials = getCredentials("prepmode");  
    	
    	AmazonIdentityManagementClient iamClient = new AmazonIdentityManagementClient(credentials);
    	//iamClient.setRegion(Lab41.region);
    	
        String trustRelationshipSource = readTextFile("TrustRelationship.txt");
        String developmentPolicyText = readTextFile("development_role.txt");
        String productionPolicyText = readTextFile("production_role.txt");

        // Clean up environment by removing the roles if they exist. 
        optionalLabCode.prepMode_RemoveRoles(iamClient, "development_role", "production_role");

        // Trust relationships for roles (the way we're using them) require the ARN of the user.
        String userArn = labCode.prepMode_GetUserArn(iamClient, LabUserName);
        System.out.println("ARN for " + LabUserName + " is " + userArn);
        String trustRelationship = trustRelationshipSource.replaceAll("\\{userArn\\}", userArn);
        System.out.println("Trust relationship policy:\n" + trustRelationship);

        // Create the roles and store the role ARNs
        labVariables.setDevelopmentRoleArn(labCode.prepMode_CreateRole(iamClient, "development_role", developmentPolicyText, trustRelationship));
        labVariables.setProductionRoleArn(labCode.prepMode_CreateRole(iamClient, "production_role", productionPolicyText, trustRelationship));

        System.out.println("Created development policy role: " + labVariables.getDevelopmentRoleArn());
        System.out.println("Created production policy role: " + labVariables.getProductionRoleArn());

        // Create the bucket names
        
        String identifier = UUID.randomUUID().toString().substring(0, 8);
        labVariables.getBucketNames().add("dev"+identifier);
        labVariables.getBucketNames().add("prod" + identifier);

        // Create the buckets
        AmazonS3Client s3Client = new AmazonS3Client(credentials);
        s3Client.setRegion(Lab41.region);
        for (String bucketName: labVariables.getBucketNames())
        {
        	optionalLabCode.prepMode_CreateBucket(s3Client, bucketName, region);
            System.out.println("Created bucket: " + bucketName);
        }
 
        return labVariables;
	}
	
	public void appMode_Run(LabVariables labVariables) throws InterruptedException, IOException {
    	AWSCredentials credentials = getCredentials("appmode");	
		
        Credentials devCredentials=null, prodCredentials=null;
        AWSSecurityTokenServiceClient stsClient = new AWSSecurityTokenServiceClient(credentials);
        //stsClient.setRegion(Lab41.region);
        
        System.out.println("\nAssuming developer role to retrieve developer session credentials.");
        Boolean retry;
        long start = System.currentTimeMillis();
        do
        {
            try
            {
                devCredentials = labCode.appMode_AssumeRole(stsClient, labVariables.getDevelopmentRoleArn(), "dev_session");
                retry = false;
            }
            catch (AmazonServiceException ase)
            {
                if (ase.getErrorCode().equals("AccessDenied"))
                {
                    // If we get access denied, the policy that we created hasn't fully propagated through STS
                    // so we need to wait and retry. This code will retry for 30 seconds before timing out.
                	long now = System.currentTimeMillis();
                    if (now>=(start+30*1000))
                    {
                        System.out.println();
                        throw ase; // Stop waiting.
                    }
                    retry = true;
                    System.out.print(".");                    
                    // Sleep for a second before trying again.
                    Thread.sleep(1000);
                }
                else
                {
                    throw ase;
                }
            }
        } while (retry); 


        System.out.println("\nAssuming production role to retrieve production session credentials.");
        
        start = System.currentTimeMillis();
        do
        {
            try
            {
                prodCredentials = labCode.appMode_AssumeRole(stsClient, labVariables.getProductionRoleArn(), "prod_session");
                retry = false;
            }
            catch (AmazonServiceException ase)
            {
                if (ase.getErrorCode().equals("AccessDenied"))
                {
                    // If we get access denied, the policy that we created hasn't fully propagated through STS
                    // so we need to wait and retry. This code will retry for 30 seconds before timing out.
                	long now = System.currentTimeMillis();
                    if (now>=(start+30*1000))
                    {
                        System.out.println();
                        throw ase; // Stop waiting.
                    }
                    retry = true;
                    System.out.print(".");                    
                    // Sleep for a second before trying again.
                    Thread.sleep(1000);
                }
                else
                {
                    throw ase;
                }
            }
        } while (retry); 

        System.out.println("\nCreating S3 client objects.");
        
        AmazonS3Client devS3Client = labCode.appMode_CreateS3Client(devCredentials, Lab41.region);
        AmazonS3Client prodS3Client = labCode.appMode_CreateS3Client(prodCredentials, Lab41.region);
            

        System.out.println("\nTesting Developer Session...");

        // Create the dev credentials.
        BasicSessionCredentials devSession = new BasicSessionCredentials(
                devCredentials.getAccessKeyId(), 
                devCredentials.getSecretAccessKey(), 
                devCredentials.getSessionToken());

        
        // Test services access using the dev credentials.
        System.out.println("  IAM: " + (optionalLabCode.appMode_TestIamAccess(Lab41.region, devSession) ? "Accessible." : "Inaccessible."));
        System.out.println("  SQS: " + (optionalLabCode.appMode_TestSqsAccess(Lab41.region, devSession) ? "Accessible." : "Inaccessible."));
        System.out.println("  SNS: " + (optionalLabCode.appMode_TestSnsAccess(Lab41.region, devSession) ? "Accessible." : "Inaccessible."));
        System.out.println("  S3:");
        for (String bucketName: labVariables.getBucketNames())
        {
            testS3Client(devS3Client, bucketName);
        }

        System.out.println("\nTesting Production Session...");
        // Create the prod credentials.
        BasicSessionCredentials prodSession = new BasicSessionCredentials(
                prodCredentials.getAccessKeyId(), 
                prodCredentials.getSecretAccessKey(), 
                prodCredentials.getSessionToken());

        // Test services using the prod credentials.
        System.out.println("  IAM: " + (optionalLabCode.appMode_TestIamAccess(Lab41.region, prodSession) ? "Accessible." : "Inaccessible."));
        System.out.println("  SQS: " + (optionalLabCode.appMode_TestSqsAccess(Lab41.region, prodSession) ? "Accessible." : "Inaccessible."));
        System.out.println("  SNS: " + (optionalLabCode.appMode_TestSnsAccess(Lab41.region, prodSession) ? "Accessible." : "Inaccessible."));
        System.out.println("  S3:");
        for (String bucketName: labVariables.getBucketNames())
        {
            testS3Client(prodS3Client, bucketName);
        }		
	}
	
    /**
     * Open a text file and read its contents into a string.
     * 
     * @param fileName
     * 			The name of the file to read.
     * 
     * @return
     * 			The contents of the file.
     * 
     * @throws IOException
     * 			If the file cannot be read.
     */
    private String readTextFile(String fileName) throws IOException {
        StringBuilder contents = new StringBuilder();
        
        try {
        	BufferedReader input =  new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "utf8"));
        	try {
        		String line = null;
        		while (( line = input.readLine()) != null) {
        			contents.append(line);
        			contents.append(System.getProperty("line.separator"));
        		}
        	}
        	finally {
        		input.close();
        	}
        }
        catch (IOException ex){
        	ex.printStackTrace();
        }
        
        // There's a bug in the reader code that puts a ? at the beginning of the string. 
        // It's an encoding error, but I can't figure out how to fix it so this workaround is in place. 
        return contents.toString().substring(1);
    	
    }
    
    public void testS3Client (AmazonS3Client s3Client, String bucketName) {
        String fileName = "test-image.png";

        System.out.print("    Uploading to bucket " + bucketName + ". ");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(fileName));

        try
        {
            s3Client.putObject(putObjectRequest);
            System.out.println("Succeeded.");
        }
        catch (AmazonS3Exception ase)
        {
        	System.out.println("Failed. [" + ase.getErrorCode() + "]") ;
        	
        }
        catch (Exception ex)
        {
        	System.out.println("Failed.");
        }

    }
    
    public AWSCredentials getCredentials (String mode) throws IOException {
    	AWSCredentials credentials=null;
    	String propFileName = "AwsCredentials.properties";
    	File propFile = new File(propFileName);
    	if (!propFile.exists()) {
    		throw new FileNotFoundException("File doesn't exist:  " + propFile.getAbsolutePath());
    	}
    	
    	Properties properties = new Properties();
    	properties.load(new FileInputStream(propFile));
    	if (mode.toLowerCase().equals("prepmode")) {
    		if (properties.getProperty("prepAccessKey")==null || properties.getProperty("prepSecretKey")==null) {
    			throw new IllegalArgumentException("The specified file (" + propFile.getAbsolutePath() + ") " +
                    "doesn't contain the expected properties 'prepAccessKey' and 'prepSecretKey'.");
    		}	

        	credentials = new BasicAWSCredentials(properties.getProperty("prepAccessKey"), properties.getProperty("prepSecretKey"));
    	}
    	else if (mode.toLowerCase().equals("appmode")) {
    		if (properties.getProperty("appAccessKey")==null || properties.getProperty("appSecretKey")==null) {
        		throw new IllegalArgumentException("The specified file (" + propFile.getAbsolutePath() + ") " +
                        "doesn't contain the expected properties 'appAccessKey' and 'appSecretKey'.");
        	}

        	// Store the appropriate credentials.
        	credentials = new BasicAWSCredentials(properties.getProperty("appAccessKey"), properties.getProperty("appSecretKey"));		
    	}
    	
    	return credentials;
    }
    // END NON-STUDENT CODE

}
