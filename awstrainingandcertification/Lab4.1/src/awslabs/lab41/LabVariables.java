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

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Lab4.1
 */
public class LabVariables {

	private String productionRoleArn=""; 
    private String developmentRoleArn="";
    private List<String> bucketNames = new ArrayList<String>(); 
    
    public String getProductionRoleArn() {
    	return productionRoleArn;
    }
    
    public void setProductionRoleArn(String value) {
     	productionRoleArn = value;
    }
    
    public LabVariables withProductionRoleArn(String value) {
     	this.setProductionRoleArn(value);
     	return this;
    }
     
    public String getDevelopmentRoleArn() {
    	return developmentRoleArn;
    }
    
    public void setDevelopmentRoleArn(String value) {
     	developmentRoleArn = value;
    }
    
    public LabVariables withDevelopmentRoleArn(String value) {
     	this.setDevelopmentRoleArn(value);
    	return this;
    }
     
    public List<String> getBucketNames() {
     	return bucketNames;
    }
    
    public LabVariables withBucketNames(String... values) {
     	for(String value: values) {
     		bucketNames.add(value);
     	}
     	
     	return this;
    }
 
}
