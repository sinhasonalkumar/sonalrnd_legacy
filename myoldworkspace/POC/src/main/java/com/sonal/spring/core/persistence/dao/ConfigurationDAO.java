package com.sonal.spring.core.persistence.dao;



import org.springframework.stereotype.Repository;

import com.sonal.spring.core.persistence.domain.ConfigurationDO;

@Repository
public class ConfigurationDAO implements IConfigurationDAO {

	@Override
	public ConfigurationDO findByConfigurationId(Long id) {
		ConfigurationDO configurationDO = new ConfigurationDO();
		configurationDO.setId(id);
		configurationDO.setConf1("config1");
		configurationDO.setConf2("config2");
		return configurationDO;
	}

	
}
