package com.sonal.spring.poc.configurationstore.configurations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PackagingConfiguration {
	private String sourceFileLocaction;
	private String targetLocationToPublish;
	private String packager;
	public String getSourceFileLocaction() {
		return sourceFileLocaction;
	}
	public void setSourceFileLocaction(String sourceFileLocaction) {
		this.sourceFileLocaction = sourceFileLocaction;
	}
	public String getTargetLocationToPublish() {
		return targetLocationToPublish;
	}
	public void setTargetLocationToPublish(String targetLocationToPublish) {
		this.targetLocationToPublish = targetLocationToPublish;
	}
	public String getPackager() {
		return packager;
	}
	public void setPackager(String packager) {
		this.packager = packager;
	}
	

	
	

}
