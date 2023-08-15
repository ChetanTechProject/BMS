package com.example.paymentservice.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.example.paymentservice.dto.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/payment")
@RestController
public class PaymentController {

	   @CrossOrigin
	   @PostMapping("/submitPaymentDetails")
	   public String submitPaymentDetail1(@RequestBody PaymentRequestDTO paymentRequestDTO) throws Exception {
            final String PAYMENT_SUCSESS = "SUCCESS";
		    final String PAYMENT_FAILED= "FAILED";
			if(paymentRequestDTO != null && paymentRequestDTO.getUserId() !=null && paymentRequestDTO.getOrderId() != null){
				System.out.println("Payment Success for User " + paymentRequestDTO.getUserId() +  " & OrderId " + paymentRequestDTO.getOrderId());
				return PAYMENT_SUCSESS;
			} else{
				return PAYMENT_FAILED;
			}

	    }
}

