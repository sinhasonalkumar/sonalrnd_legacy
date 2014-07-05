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
package awslabs.lab51;

import com.amazonaws.internal.EC2MetadataClient;
import com.amazonaws.util.json.JSONObject;

/**
 * Project: Lab5.1
 */
public class InstanceIdentity {
	//private final String EC2_METADATA_SERVICE_URL = "http://169.254.169.254";
	
	private String instanceId="(no instance metadata)";
    private String version="(no instance metadata)";
    private String region="(no instance metadata)";
    private String accountId="(no instance metadata)";
    private String imageId="(no instance metadata)";
    private String kernelId="(no instance metadata)"; 
    private String ramdiskId="(no instance metadata)";
    private String architecture="(no instance metadata)";
    private String pendingTime="(no instance metadata)";
    private String availabilityZone="(no instance metadata)";
    private String privateIp="(no instance metadata)";
    private String instanceType="(no instance metadata)";
    
    public InstanceIdentity() {
    	try {
        	String instanceMetadata = new EC2MetadataClient().readResource("/latest/dynamic/instance-identity/document");
        	JSONObject jsonObject = new JSONObject(instanceMetadata);
        	
        	if (jsonObject.has("instanceId")) {
        		instanceId = jsonObject.getString("instanceId");
        	}
        	if (jsonObject.has("version")) {
        		version = jsonObject.getString("version");
        	}
        	if (jsonObject.has("region")) {
        		region = jsonObject.getString("region");
        	}
        	if (jsonObject.has("accountId")) {
        		accountId = jsonObject.getString("accountId");
        	}
        	if (jsonObject.has("imageId")) {
        		imageId = jsonObject.getString("imageId");
        	}
        	if (jsonObject.has("kernelId")) {
        		kernelId = jsonObject.getString("kernelId");
        	}
        	if (jsonObject.has("ramdiskId")) {
        		ramdiskId = jsonObject.getString("ramdiskId");
        	}
        	if (jsonObject.has("architecture")) {
        		architecture = jsonObject.getString("architecture");
        	}
        	if (jsonObject.has("pendingTime")) {
        		pendingTime = jsonObject.getString("pendingTime");
        	}
        	if (jsonObject.has("availabilityZone")) {
        		availabilityZone = jsonObject.getString("availabilityZone");
        	}
        	if (jsonObject.has("privateIp")) {
        		privateIp = jsonObject.getString("privateIp");
        	}
        	if (jsonObject.has("instanceType")) {
        		instanceType = jsonObject.getString("instanceType");
        	}
    	}
    	catch (Exception ex) {
    		// If we have an exception, just stop processing and use the default values.
    	}

    }
	
    public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getKernelId() {
		return kernelId;
	}
	public void setKernelId(String kernelId) {
		this.kernelId = kernelId;
	}
	public String getRamdiskId() {
		return ramdiskId;
	}
	public void setRamdiskId(String ramdiskId) {
		this.ramdiskId = ramdiskId;
	}
	public String getArchitecture() {
		return architecture;
	}
	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
	public String getPendingTime() {
		return pendingTime;
	}
	public void setPendingTime(String pendingTime) {
		this.pendingTime = pendingTime;
	}
	public String getAvailabilityZone() {
		return availabilityZone;
	}
	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}
	public String getPrivateIp() {
		return privateIp;
	}
	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}
    
    public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
