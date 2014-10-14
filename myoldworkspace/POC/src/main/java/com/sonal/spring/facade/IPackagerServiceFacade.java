package com.sonal.spring.facade;

import java.util.Map;

public interface IPackagerServiceFacade {
	
	Map<String, Boolean> packageAndPublish(Long appId) throws Exception;
}
