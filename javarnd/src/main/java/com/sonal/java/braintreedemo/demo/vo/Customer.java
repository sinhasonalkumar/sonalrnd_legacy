package com.sonal.java.braintreedemo.demo.vo;

import java.util.Set;

public class Customer {
	
	private String customerId;
	
	private Set<CustomerCreditCard> creditCards;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Set<CustomerCreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(Set<CustomerCreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	
	

}
