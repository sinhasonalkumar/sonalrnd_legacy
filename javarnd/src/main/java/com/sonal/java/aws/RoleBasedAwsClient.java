package com.sonal.java.aws;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class RoleBasedAwsClient {

	public static void main(String[] args) throws Throwable {

		// Local Code
		//BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
		//AmazonS3Client s3Client = new AmazonS3Client(credentials);

		// Actual Code
		 AmazonS3Client s3Client = new AmazonS3Client();
		String bucketName = "fv-local";
		String key = "api/braintree-client.properties";
		S3Object brainTreeFileOnS3 = s3Client.getObject(new GetObjectRequest(bucketName, key));
		S3ObjectInputStream objectContent = brainTreeFileOnS3.getObjectContent();

		String merchantId = null;
		String publicKey = null;
		String privateKey = null;

		BufferedReader reader = new BufferedReader(new InputStreamReader(objectContent));
		while (true) {

			String line = reader.readLine();
			if (line == null)
				break;
			if (!line.startsWith("#")) {
				if (line.contains("braintree.gateway.merchantId")) {
					merchantId = line.split("=")[1];
				}
				if (line.contains("braintree.gateway.publicKey")) {
					publicKey = line.split("=")[1];
				}
				if (line.contains("braintree.gateway.privateKey")) {
					privateKey = line.split("=")[1];
				}
				System.out.println("    " + line);
			}

		}
		System.out.println("merchantId :: " + merchantId);
		System.out.println("publicKey :: " + publicKey);
		System.out.println("privateKey :: " + privateKey);
	}
}
