package com.sonal.spring.test.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sonal.spring.test.configuration.ConfigSource;
import com.sonal.spring.test.dao.ConfigDAO;
import com.sonal.spring.test.factory.ConfigurationFactory;

@Component(value = "configurationSourceAsProps")
public class ConfigurationServiceImpl implements ConfigurationService {
	
	@Autowired
	private ConfigDAO configDAO;
	
	@Autowired
	private ConfigurationFactory configurationFactory;

	@Override
	public Map<String, String> getConfiguration(Object source) {
		String configSourceType = configDAO.findSource((String)source);
		ConfigSource configSource = configurationFactory.getConfigSource(configSourceType);
		 Map<String, String> configMap = ( Map<String, String>)configSource.loadConig();
		return configMap;
	}
	
	public ConfigDAO getConfigDAO() {
		return configDAO;
	}

	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}
	
	

}
