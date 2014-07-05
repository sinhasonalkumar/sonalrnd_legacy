package com.sonal.java.braintreedemo.demo.service;

import java.util.List;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.Environment;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionSearchRequest;
import com.sonal.java.braintreedemo.demo.vo.Merchant;

public class TransactionSearchService {

	public Customer findCustomerTransactionHistroy(String brainTreeRetailId){
		
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, Merchant.BRAINTREE_MERCHANT_ID.getValue(), Merchant.BRAINTREE_PUBLIC_KEY.getValue(), Merchant.BRAINTREE_PRIVATE_KEY.getValue());

		Customer customer = gateway.customer().find(brainTreeRetailId);
		List<CreditCard> creditCards = customer.getCreditCards();
		for (CreditCard creditCard : creditCards) {
			System.out.println(creditCard.getCardholderName());
			System.out.println(creditCard.getToken());
			System.out.println(creditCard.getMaskedNumber());
			System.out.println(creditCard.getExpirationMonth());
			System.out.println(creditCard.getExpirationYear());
			
			TransactionSearchRequest request = new TransactionSearchRequest().creditCardNumber().endsWith((creditCard.getLast4()));
			ResourceCollection<Transaction> collection = gateway.transaction().search(request);
			Transaction first = collection.getFirst();
			for (Transaction transaction : collection) {
				System.out.println(transaction.getAmount());
				System.out.println(transaction.getId());
			}
			
			System.out.println("------------------------------");
			
		}
		
		
		
		return null;
	}
}
