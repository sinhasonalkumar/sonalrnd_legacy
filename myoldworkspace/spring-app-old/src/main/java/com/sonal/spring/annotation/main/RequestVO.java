package com.sonal.spring.annotation.main;

public class RequestVO {

	private String name;
    private String id;
    
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String values = "[ name = " + this.name + ", id = " + this.id + " ]";
		return values;
	}
    
    
}
