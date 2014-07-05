package com.sonal.java.braintreedemo.demo.vo;

public enum Merchant {

	BRAINTREE_MERCHANT_ID("vmb9ypb3p5hmnz78"),BRAINTREE_PUBLIC_KEY("vs4nrt5xbdjzk2mf"),BRAINTREE_PRIVATE_KEY("fd38a3b6cf31276b26df6b2483bcb57e");
	
	private String value;
	
	private Merchant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	
	
}
