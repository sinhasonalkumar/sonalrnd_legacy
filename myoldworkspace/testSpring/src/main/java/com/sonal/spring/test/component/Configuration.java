package com.sonal.spring.test.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sonal.spring.test.annotation.ConfigureConfiguration;
import com.sonal.spring.test.manager.ConfigurationManager;

@Component
public class Configuration {

	@Autowired
	private ConfigurationManager configurationManager;

	@ConfigureConfiguration(key = "com.test.conf1", source = "src1")
	private String conf1;
	@ConfigureConfiguration(key = "com.test.conf2")
	private String conf2;
	@ConfigureConfiguration(key = "com.test.conf3", source = "src2")
	private String conf3;
	@ConfigureConfiguration(key = "com.test.conf4")
	private String conf4;

	@ConfigureConfiguration(key = "com.test.conf5")
	private String conf5;
	@ConfigureConfiguration(key = "com.test.conf6")
	private String conf6;

	public void show() {
		System.out.println(getConf1());
		System.out.println(getConf2());
		System.out.println(getConf3());
		System.out.println(getConf4());
		System.out.println(getConf5());
		System.out.println(getConf6());
	}

	public String getConf1() {
		return conf1;
	}

	public void setConf1(String conf1) {
		this.conf1 = conf1;
	}

	public String getConf2() {
		return conf2;
	}

	public void setConf2(String conf2) {
		this.conf2 = conf2;
	}

	public String getConf3() {
		return conf3;
	}

	public void setConf3(String conf3) {
		this.conf3 = conf3;
	}

	public String getConf4() {
		return conf4;
	}

	public void setConf4(String conf4) {
		this.conf4 = conf4;
	}

	public String getConf5() {
		return conf5;
	}

	public void setConf5(String conf5) {
		this.conf5 = conf5;
	}

	public String getConf6() {
		return conf6;
	}

	public void setConf6(String conf6) {
		this.conf6 = conf6;
	}

	@PostConstruct
	public void initConf() throws Exception {
		configurationManager.buildConfiguration(this);
	}

}
