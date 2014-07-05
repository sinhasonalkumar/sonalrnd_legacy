package com.sonal.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentFormController { 

    @RequestMapping(value = "/customerPaymentForm",method = RequestMethod.GET)
    public String loadPaymentForm(HttpServletRequest req,HttpServletResponse res,Model model) {
    	String braintreeUrl = req.getHeader("braintreeUrl");
    	String trData = req.getHeader("trData");
    	System.out.println(braintreeUrl);
    	System.out.println(trData);
        model.addAttribute("braintreeUrl", braintreeUrl);
        model.addAttribute("trData", trData);
        
        return "paymentForm";
    }
    
    @RequestMapping(value = "/customerPaymentFormTest",method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("braintreeUrl", "https://sandbox.braintreegateway.com:443/merchants/vmb9ypb3p5hmnz78/transparent_redirect_requests");
        model.addAttribute("trData", "1cd1f742d8690c0a4ad49c2c93f5aa1dce9d4446|api_version=3&public_key=vs4nrt5xbdjzk2mf&redirect_url=http%3A%2F%2Flocalhost%3A4567%2Fbraintree&time=20140628042307&kind=create_customer");
        
        return "/paymentForm";
    }

}
