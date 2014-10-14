package com.sonal.spring.test.factory;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sonal.spring.test.configuration.ConfigSource;

@Component
public class ConfigurationFactory {
	
	@Autowired
	@Qualifier("dBBasedConfig")
	private ConfigSource dBBasedConfig;	
	
	@Autowired
	@Qualifier("propBasedConfig")
	private ConfigSource propBasedConfig;
	

	public ConfigSource getConfigSource(String sourceType){
		ConfigSource configSource = null;
		if(sourceType.equalsIgnoreCase("DB")){
			configSource = dBBasedConfig;
		}else{
			configSource = propBasedConfig;
		}
		
		return configSource;
	}

}
