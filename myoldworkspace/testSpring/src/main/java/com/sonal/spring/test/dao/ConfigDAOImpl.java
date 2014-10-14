package com.sonal.spring.test.dao;


import org.springframework.stereotype.Repository;

@Repository
public class ConfigDAOImpl implements ConfigDAO {

	@Override
	public String findSource(String source) {
		String configSource = null;
		if(source.equalsIgnoreCase("src1")){
			configSource = "DB";
		}
		else{
			configSource = "Properties";
		}
		return configSource;
	}

	
}
