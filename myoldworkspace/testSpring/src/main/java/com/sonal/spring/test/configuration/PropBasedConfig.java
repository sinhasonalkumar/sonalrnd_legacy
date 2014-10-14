package com.sonal.spring.test.configuration;


import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

@Component(value = "propBasedConfig")
public class PropBasedConfig implements ConfigSource{

	@Override
	public Object loadConig() {
		Map<String, String> configMap = new HashedMap();
		configMap.put("com.test.conf1", "conf1Prop");
		configMap.put("com.test.conf2", "conf2Prop");
		configMap.put("com.test.conf3", "conf3Prop");
		configMap.put("com.test.conf4", "conf4Prop");
		configMap.put("com.test.conf5", "conf5Prop");
		configMap.put("com.test.conf6", "conf6Prop");
		return configMap;
	}

	
}
