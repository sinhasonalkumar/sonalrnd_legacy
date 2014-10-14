package com.sonal.spring.poc.configurationstore.configurations;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Configuration {
	
	private List<PackagingConfiguration> packageConfigurations;

	public List<PackagingConfiguration> getPackageConfigurations() {
		return packageConfigurations;
	}

	public void setPackageConfigurations(List<PackagingConfiguration> packageConfigurations) {
		this.packageConfigurations = packageConfigurations;
	}	

}
