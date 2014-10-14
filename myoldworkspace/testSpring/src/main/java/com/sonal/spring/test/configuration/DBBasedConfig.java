package com.sonal.spring.test.configuration;



import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

@Component(value = "dBBasedConfig")
public class DBBasedConfig implements ConfigSource {

	@Override
	public Object loadConig() {
		Map<String, String> configMap = new HashedMap();
		configMap.put("com.test.conf1", "conf1DB");
		configMap.put("com.test.conf2", "conf2DB");
		configMap.put("com.test.conf3", "conf3DB");
		configMap.put("com.test.conf4", "conf4DB");
		configMap.put("com.test.conf5", "conf5DB");
		configMap.put("com.test.conf6", "conf6DB");
		return configMap;
	}
}
