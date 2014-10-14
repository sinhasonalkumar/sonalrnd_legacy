package com.sonal.spring.poc.configurationstore.configurationfactory;

import com.sonal.spring.poc.configurationstore.configurations.Configuration;

public interface IConfigurationFactory {

	Configuration getConfiguration(Long applicationId);
}
