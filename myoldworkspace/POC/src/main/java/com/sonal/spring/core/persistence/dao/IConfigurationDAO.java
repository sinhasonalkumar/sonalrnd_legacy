package com.sonal.spring.core.persistence.dao;

import com.sonal.spring.core.persistence.domain.ConfigurationDO;

public interface IConfigurationDAO {

		ConfigurationDO findByConfigurationId(Long id);
}
