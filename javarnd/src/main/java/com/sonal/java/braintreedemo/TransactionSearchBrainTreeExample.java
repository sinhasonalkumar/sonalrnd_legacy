package com.sonal.java.braintreedemo;

import java.util.List;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.CreditCardVerification;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.StatusEvent;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionSearchRequest;

public class TransactionSearchBrainTreeExample {

	private static String BRAINTREE_MERCHANT_ID = "vmb9ypb3p5hmnz78";
	private static String BRAINTREE_PUBLIC_KEY = "vs4nrt5xbdjzk2mf";
	private static String BRAINTREE_PRIVATE_KEY = "fd38a3b6cf31276b26df6b2483bcb57e";

	public static void main(String[] args) {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, BRAINTREE_MERCHANT_ID, BRAINTREE_PUBLIC_KEY, BRAINTREE_PRIVATE_KEY);

		
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest().creditCardCardType().in(CreditCard.CardType.VISA, CreditCard.CardType.MASTER_CARD);

		ResourceCollection<Transaction> collection = gateway.transaction().search(transactionSearchRequest);
		Transaction first = collection.getFirst();
		List<StatusEvent> statusHistory = first.getStatusHistory();
		for (StatusEvent statusEvent : statusHistory) {
			System.out.println(statusEvent.getUser());
			System.out.println(statusEvent.getStatus());
			System.out.println(statusEvent.getAmount());
		}

	}

	

}
