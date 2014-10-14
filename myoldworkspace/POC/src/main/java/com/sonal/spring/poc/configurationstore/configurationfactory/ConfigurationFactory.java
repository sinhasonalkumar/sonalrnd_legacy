package com.sonal.spring.poc.configurationstore.configurationfactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sonal.spring.core.persistence.dao.IConfigurationDAO;
import com.sonal.spring.core.persistence.domain.ConfigurationDO;
import com.sonal.spring.poc.configurationstore.configurations.Configuration;
import com.sonal.spring.poc.configurationstore.configurations.PackagingConfiguration;

@Component
public class ConfigurationFactory implements IConfigurationFactory {

	@Autowired
	private IConfigurationDAO configurationDAO; 
	@Override
	public Configuration getConfiguration(Long applicationId) {
		
		ConfigurationDO configurationDO = configurationDAO.findByConfigurationId(Long.valueOf(applicationId));
		Configuration configuration =  buildConfigurationObject(configurationDO);
		return configuration;
	}
	
	private Configuration buildConfigurationObject(ConfigurationDO configurationDO){
		Configuration configuration = new Configuration();
		
		List<PackagingConfiguration> packagingConfigurations = new ArrayList<PackagingConfiguration>();
		
		
		PackagingConfiguration packagingConfiguration1 = new PackagingConfiguration();
		PackagingConfiguration packagingConfiguration2 = new PackagingConfiguration();
		packagingConfigurations.add(packagingConfiguration1);
		packagingConfigurations.add(packagingConfiguration2);
		
		
		packagingConfiguration1.setPackager("flashPackager");
		packagingConfiguration1.setTargetLocationToPublish("CDN1");
		packagingConfiguration1.setSourceFileLocaction("Burfiler1");
		
		packagingConfiguration2.setPackager("widevinePackager");
		packagingConfiguration2.setTargetLocationToPublish("CDN2");
		packagingConfiguration2.setSourceFileLocaction("Burfiler1");
		
		configuration.setPackageConfigurations(packagingConfigurations);
		
		return configuration;
	}
	
	
}
