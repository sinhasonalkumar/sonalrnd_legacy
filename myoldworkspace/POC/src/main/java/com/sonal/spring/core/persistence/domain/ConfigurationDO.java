package com.sonal.spring.core.persistence.domain;

public class ConfigurationDO {

	private long id;
	private String conf1;
	private String conf2;
	public long getId() {
		return id;
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
	public void setId(long id) {
		this.id = id;
	}
	
	
}
