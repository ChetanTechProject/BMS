package com.example.paymentservice.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/payment")
@RestController
public class PaymentController {


	   @GetMapping(value = "/submitPaymentDetail1")
	    public String submitPaymentDetail1(@RequestParam(name = "USER_ID") Long userId,
	                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
	                                    @RequestParam(name = "ORDER_ID") Long orderId) throws Exception {
            final String PAYMENT_SUCSESS = "SUCCESS";
		    final String PAYMENT_FAILED= "FAILED";

			if(userId !=null && orderId != null){
				System.out.println("Payment Success for User " + userId +  " & OrderId " + orderId);
				return PAYMENT_SUCSESS;
			} else{
				return PAYMENT_FAILED;
			}

	    }

	@GetMapping(value = "/submitPaymentDetail")
	public String submitPaymentDetail() throws Exception {
		final String PAYMENT_SUCSESS = "SUCCESS";

			System.out.println("Payment Successful");
			return PAYMENT_SUCSESS;
	}
}

