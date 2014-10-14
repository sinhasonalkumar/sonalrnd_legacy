package com.sonal.spring;


import org.springframework.context.ApplicationContext;

import com.sonal.spring.facade.IPackagerServiceFacade;
import com.sonal.spring.facade.PackagerServiceFacade;
import com.sonal.spring.poc.util.ApplicationContextUtil;

public class MainProg {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = ApplicationContextUtil.getInstance().getApplicationContext();
		IPackagerServiceFacade packagerServiceFacade = (PackagerServiceFacade)ctx.getBean("packagerServiceFacade");
		//projectID for DC2 is 1
		packagerServiceFacade.packageAndPublish(1L);		
	}
	
	
	
	

}
