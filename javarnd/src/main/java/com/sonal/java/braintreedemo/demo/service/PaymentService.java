package com.sonal.java.braintreedemo.demo.service;

import java.math.BigDecimal;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.sonal.java.braintreedemo.demo.vo.CustomerCreditCard;
import com.sonal.java.braintreedemo.demo.vo.CustomersOrder;
import com.sonal.java.braintreedemo.demo.vo.Merchant;

public class PaymentService {

	public CustomersOrder doCCTransactionOnNewCutomer(CustomerCreditCard cc, BigDecimal amount) {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, Merchant.BRAINTREE_MERCHANT_ID.getValue(), Merchant.BRAINTREE_PUBLIC_KEY.getValue(), Merchant.BRAINTREE_PRIVATE_KEY.getValue());

		TransactionRequest request = new TransactionRequest()
				.amount(amount)
				.creditCard().number(cc.getCreditCardNumber()).expirationMonth(cc.getExpirationMonth()).expirationYear(cc.getExpirationYear()).done()
				.customer().firstName(cc.getFirstName()).done()
				.shippingAddress().streetAddress(cc.getShippingAdd()).done()
				.billingAddress().streetAddress(cc.getBillingAdd()).done()
				.options().storeInVault(true).storeShippingAddressInVault(true).done();
		
		Result<Transaction> transactionResult = gateway.transaction().sale(request);
		
		Customer customer = transactionResult.getTarget().getCustomer();
		System.out.println(customer.getId());
		
		String brainTreeCustomerRetialId = transactionResult.getTarget().getId();
		String creditCardToken  = transactionResult.getTarget().getCreditCard().getToken();
		System.out.println(brainTreeCustomerRetialId);
		System.out.println(creditCardToken);
		

		return null;
	}
	
	public CustomersOrder doCCTransactionOnExistingCustomer(CustomerCreditCard cc, BigDecimal amount) {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, Merchant.BRAINTREE_MERCHANT_ID.getValue(), Merchant.BRAINTREE_PUBLIC_KEY.getValue(), Merchant.BRAINTREE_PRIVATE_KEY.getValue());

		TransactionRequest request = new TransactionRequest()
				.amount(amount)
				
				.customerId(cc.getBrainTreeRetailId())
				
				.creditCard().number(cc.getCreditCardNumber()).expirationMonth(cc.getExpirationMonth()).expirationYear(cc.getExpirationYear()).done()
				.customer().firstName(cc.getFirstName()).done()
				.shippingAddress().streetAddress(cc.getShippingAdd()).done()
				.billingAddress().streetAddress(cc.getBillingAdd()).done()
				.options().storeInVault(true).storeShippingAddressInVault(true).done();
		
		Result<Transaction> transactionResult = gateway.transaction().sale(request);
		
		Customer customer = transactionResult.getTarget().getCustomer();
		System.out.println(customer.getId());
		
		String brainTreeCustomerRetialId = transactionResult.getTarget().getId();
		String creditCardToken  = transactionResult.getTarget().getCreditCard().getToken();
		
		System.out.println(brainTreeCustomerRetialId);
		System.out.println(creditCardToken);
		

		return null;
	}
}
