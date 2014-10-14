package com.sonal.spring.dynamicloading;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "MyApp:name=MyMbean")
public class MyMbean {
	
	@ManagedOperation(description = "Returns something.")
    public String getSomething() {
        return "Something";
    }
}
