package com.sonal.java.braintreedemo;

import java.math.BigDecimal;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;

public class PaymentBraintreeExample {

	private static String BRAINTREE_MERCHANT_ID = "vmb9ypb3p5hmnz78";
	private static String BRAINTREE_PUBLIC_KEY = "vs4nrt5xbdjzk2mf";
	private static String BRAINTREE_PRIVATE_KEY = "fd38a3b6cf31276b26df6b2483bcb57e";

	public static void main(String[] args) {
//		Result<Transaction> result = doTransactionWithNewCustomer();
//		String customerId = result.getTarget().getCustomer().getId();
//		String ccToken = result.getTarget().getCreditCard().getToken();
//		String uniqueNumberIdentifier = result.getTarget().getCreditCard().getUniqueNumberIdentifier();
//		
		doTransactionWithExistingCustomer();
	}

	public static Result<Transaction> doTransactionWithNewCustomer() {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, BRAINTREE_MERCHANT_ID, BRAINTREE_PUBLIC_KEY, BRAINTREE_PRIVATE_KEY);

		
		
		TransactionRequest request = new TransactionRequest().amount(new BigDecimal("1.00")).creditCard().number("4111111111111111").expirationMonth("05").expirationYear("2015").done();
		request.options()
        .storeInVaultOnSuccess(true);

		Result<Transaction> result = gateway.transaction().sale(request);
		

		if (result.isSuccess()) {
			Transaction transaction = result.getTarget();
			System.out.println("Success!: " + transaction.getId());

		} else if (result.getTransaction() != null) {
			System.out.println("Message: " + result.getMessage());
			Transaction transaction = result.getTransaction();
			System.out.println("Error processing transaction:");
			System.out.println("  Status: " + transaction.getStatus());
			System.out.println("  Code: " + transaction.getProcessorResponseCode());
			System.out.println("  Text: " + transaction.getProcessorResponseText());
		} else {
			System.out.println("Message: " + result.getMessage());
			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				System.out.println("Attribute: " + error.getAttribute());
				System.out.println("  Code: " + error.getCode());
				System.out.println("  Message: " + error.getMessage());
			}
		}
		
		
		
		return result;
	}
	
	public static Result<Transaction> doTransactionWithExistingCustomer() {
		BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, BRAINTREE_MERCHANT_ID, BRAINTREE_PUBLIC_KEY, BRAINTREE_PRIVATE_KEY);
		
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.creditCard().number("4111111111111111").expirationMonth("05").expirationYear("2015").done();
		
		Result<Customer> createCustomerResult = gateway.customer().create(customerRequest);
		
		String customerId = createCustomerResult.getTarget().getId();
		String ccToken = createCustomerResult.getTarget().getCreditCards().get(0).getToken();
		//String ccToken = createCustomerResult.getCreditCardVerification().getCreditCard().getToken();

		TransactionRequest request = new TransactionRequest();
		//request.customerId("32910059");
		request.customerId(customerId);
		request.amount(new BigDecimal("1.00"));
		//request.paymentMethodToken("4smtmb");
		request.paymentMethodToken(ccToken);
		
		
		
		Result<Transaction> result = gateway.transaction().sale(request);

		if (result.isSuccess()) {
			Transaction transaction = result.getTarget();
			System.out.println("Success!: " + transaction.getId());

		} else if (result.getTransaction() != null) {
			System.out.println("Message: " + result.getMessage());
			Transaction transaction = result.getTransaction();
			System.out.println("Error processing transaction:");
			System.out.println("  Status: " + transaction.getStatus());
			System.out.println("  Code: " + transaction.getProcessorResponseCode());
			System.out.println("  Text: " + transaction.getProcessorResponseText());
		} else {
			System.out.println("Message: " + result.getMessage());
			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				System.out.println("Attribute: " + error.getAttribute());
				System.out.println("  Code: " + error.getCode());
				System.out.println("  Message: " + error.getMessage());
			}
		}
		return result;
	}

}
