package com.sonal.spring;

import org.springframework.context.ApplicationContext;

import com.sonal.spring.test.component.Configuration;
import com.sonal.spring.test.util.ApplicationContextUtil;

public class MainProg {

	public static void main(String[] args) {
		ApplicationContext ctx = ApplicationContextUtil.getApplicationContext();
		Configuration component1 = (Configuration)ctx.getBean("configuration");
		component1.show();
	}

}
