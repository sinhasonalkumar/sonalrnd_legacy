package com.sonal.java.braintreedemo.demo.vo;

import java.util.Set;

public class CustomerCreditCard {

	private String holderName;
	private String firstName;
	private String lastName;
	private String creditCardNumber;
	private String shippingAdd;
	private String billingAdd;
	private String brainTreeRetailId;
	private String creditCardToken;
	private String expirationMonth;
	private String expirationYear;
	private Set<CustomersOrder> cutomersOrders;
	
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getShippingAdd() {
		return shippingAdd;
	}
	public void setShippingAdd(String shippingAdd) {
		this.shippingAdd = shippingAdd;
	}
	public String getBillingAdd() {
		return billingAdd;
	}
	public void setBillingAdd(String billingAdd) {
		this.billingAdd = billingAdd;
	}
	public String getBrainTreeRetailId() {
		return brainTreeRetailId;
	}
	public void setBrainTreeRetailId(String brainTreeRetailId) {
		this.brainTreeRetailId = brainTreeRetailId;
	}
	public String getCreditCardToken() {
		return creditCardToken;
	}
	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}
	public Set<CustomersOrder> getCutomersOrders() {
		return cutomersOrders;
	}
	public void setCutomersOrders(Set<CustomersOrder> cutomersOrders) {
		this.cutomersOrders = cutomersOrders;
	}
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	
}
