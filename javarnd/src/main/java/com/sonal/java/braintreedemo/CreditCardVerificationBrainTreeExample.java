package com.sonal.java.braintreedemo;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.CreditCardVerification;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionSearchRequest;

public class CreditCardVerificationBrainTreeExample {

	private static String BRAINTREE_MERCHANT_ID = "vmb9ypb3p5hmnz78";
	private static String BRAINTREE_PUBLIC_KEY = "vs4nrt5xbdjzk2mf";
	private static String BRAINTREE_PRIVATE_KEY = "fd38a3b6cf31276b26df6b2483bcb57e";

	public static void main(String[] args) {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, BRAINTREE_MERCHANT_ID, BRAINTREE_PUBLIC_KEY, BRAINTREE_PRIVATE_KEY);

		CustomerRequest request = new CustomerRequest().firstName("ABC").lastName("XYZ").creditCard().cardholderName("ABC XYZ").number("4111111111111111").cvv("123").expirationDate("05/2020").options().verifyCard(true).done().done();

		Result<Customer> result = gateway.customer().create(request);
		Customer customer = result.getTarget();
		System.out.println("Customer ID : " + customer.getId());
		System.out.println("Token : " + customer.getCreditCards().get(0).getToken());
		System.out.println(result.isSuccess());
		// false

		CreditCardVerification verification = result.getCreditCardVerification();
		if (verification != null) {
			System.out.println(verification.getStatus());
			// processor_declined
			System.out.println(verification.getProcessorResponseCode());
			// 2000
			System.out.println(verification.getProcessorResponseText());
			// Do Not Honor
		}
		
		TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest().creditCardCardType().in(CreditCard.CardType.VISA, CreditCard.CardType.MASTER_CARD);

		ResourceCollection<Transaction> collection = gateway.transaction().search(transactionSearchRequest);

	}

	

}
