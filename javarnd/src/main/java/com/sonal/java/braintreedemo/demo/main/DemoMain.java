package com.sonal.java.braintreedemo.demo.main;

import java.math.BigDecimal;

import com.sonal.java.braintreedemo.demo.service.PaymentService;
import com.sonal.java.braintreedemo.demo.service.TransactionSearchService;
import com.sonal.java.braintreedemo.demo.vo.CustomerCreditCard;

public class DemoMain {

	public static void main(String[] args) {

		PaymentService paymentService = new PaymentService();
		TransactionSearchService transactionSearchService = new TransactionSearchService();
		//CustomerCreditCard newCustomerCreditCard = getNewCustomerCreditCard();
		//paymentService.doCCTransactionOnNewCutomer(newCustomerCreditCard, BigDecimal.valueOf(1L));
		//CustomerCreditCard existingCustomerCreditCard = getExistingCustomerCreditCard();
		//paymentService.doCCTransactionOnExistingCustomer(existingCustomerCreditCard,BigDecimal.valueOf(1L));
		transactionSearchService.findCustomerTransactionHistroy("32731981");

	}

	public static CustomerCreditCard getNewCustomerCreditCard() {
		CustomerCreditCard customerCreditCard = new CustomerCreditCard();
		customerCreditCard.setBillingAdd("billingAdd");
		customerCreditCard.setCreditCardNumber("4111111111111111");
		customerCreditCard.setExpirationMonth("08");
		customerCreditCard.setExpirationYear("2015");
		customerCreditCard.setFirstName("TestFirstName");
		customerCreditCard.setLastName("TestLastName");
		customerCreditCard.setShippingAdd("shippingAdd");
		return customerCreditCard;
	}
	
	public static CustomerCreditCard getExistingCustomerCreditCard() {
		CustomerCreditCard customerCreditCard = new CustomerCreditCard();
		customerCreditCard.setBrainTreeRetailId("32731981");
		
		customerCreditCard.setBillingAdd("billingAdd");
		customerCreditCard.setCreditCardNumber("4111111111111111");
		customerCreditCard.setExpirationMonth("08");
		customerCreditCard.setExpirationYear("2015");
		customerCreditCard.setFirstName("TestFirstName");
		customerCreditCard.setLastName("TestLastName");
		customerCreditCard.setShippingAdd("shippingAdd");
		return customerCreditCard;
	}

}
