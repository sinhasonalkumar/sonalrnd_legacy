<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	${braintreeUrl}
	${trData}
	
	<h1>Braintree Credit Card Transaction Form</h1>
    <form id='payment-form' action='${braintreeUrl}' method='POST'>
      <input type='hidden' name='tr_data' value='${trData}' />
      <div>
        <h2>Customer Information</h2>
        <label for='customer_first_name'>First Name</label>
        <input type='text' name='customer[first_name]' id='customer_first_name'></input>
        <label for='customer_last_name'>Last Name</label>
        <input type='text' name='customer[last_name]' id='customer_last_name'></input>
        <label for='customer_email'>Email</label>
        <input type='text' name='customer[email]' id='customer_email'></input>
        <h2>Billing Address</h2>
        <label for='billing_street_address'>Street Address</label>
        <input type='text' name='customer[credit_card][billing_address][street_address]' id='billing_street_address'></input>
        <label for='billing_extended_address'>Extended Address</label>
        <input type='text' name='customer[credit_card][billing_address][extended_address]' id='billing_extended_address'></input>
        <label for='billing_locality'>Locality</label>
        <input type='text' name='customer[credit_card][billing_address][locality]' id='billing_locality'></input>
        <label for='billing_region'>Region</label>
        <input type='text' name='customer[credit_card][billing_address][region]' id='billing_region'></input>
        <label for='billing_postal_code'>Postal Code</label>
        <input type='text' name='customer[credit_card][billing_address][postal_code]' id='billing_postal_code'></input>
      </div>
      <div>
        <h2>Credit Card</h2>
        <label for='braintree_credit_card_number'>Credit Card Number</label>
        <input type='text' name='customer[credit_card][number]' id='braintree_credit_card_number' value='4111111111111111'></input>
        <label for='braintree_credit_card_exp'>Credit Card Expiry (mm/yyyy)</label>
        <input type='text' name='customer[credit_card][expiration_date]' id='braintree_credit_card_exp' value='12/2015'></input>
      </div>
        <input class='submit-button' type='submit' />
    </form>  

</body>
</html>