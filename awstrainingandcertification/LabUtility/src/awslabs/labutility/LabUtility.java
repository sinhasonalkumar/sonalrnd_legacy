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
package awslabs.labutility;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

/**
 * Project: LabUtility
 * 
 */
public class LabUtility {
    public static void dumpError(Exception ex)
    {
        if (ex instanceof AmazonServiceException)
        {
            AmazonServiceException ase = (AmazonServiceException)ex;
            System.out.println();
            System.out.println("Caught an AmazonServiceException, which means your request made it AWS,");
            System.out.println("but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            System.out.println("Stack trace:");
            ase.printStackTrace();
        }
        else if (ex instanceof AmazonClientException)
        {
            AmazonClientException ace = (AmazonClientException)ex;
            System.out.println();
            System.out.println("Caught an AmazonClientException, which means the client encountered");
            System.out.println("a problem without before communicating with the service.");
            System.out.println("Error Message: " + ace.getMessage());
            System.out.println("Stack trace:");
            ace.printStackTrace();
        }
        else
        {
            System.out.println();
            System.out.println("Caught exception [" + ex.getClass() + "].");
            System.out.println("Error Message: " + ex.getMessage());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Stack trace:");
            ex.printStackTrace();
        }
    }
}
