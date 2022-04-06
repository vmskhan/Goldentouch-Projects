package com.controllers;

import java.security.Principal;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Participation;
import com.entities.ParticipationPk;
import com.entities.Test;
import com.entities.User;
import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.service.OrderService;
import com.service.ParticipationService;
import com.service.PaymentService;
import com.service.QuestionService;
import com.service.TestService;
import com.service.UserService;
import com.util.CallbackResponse;
import com.util.PaymentUtils;
import com.util.RazorPay;

@Controller
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ParticipationService participationService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private OrderService orderService;
	
	private static Gson gson = new Gson();
	
	
	@RequestMapping(value="/test/{tid}/create", method=RequestMethod.POST)
	public ResponseEntity<String> createOrder(Principal principal,@PathVariable int tid){
		System.out.println("createOrder method called");
		if(principal == null) {
			return null;
		}
		User user = userService.findUserByEmailid(principal.getName());
		Test test = testService.findById(tid);
		
		ParticipationPk pk = new ParticipationPk();
		pk.setTid(tid);
		pk.setUid(user.getUid());
		try {			
			
			Order order = paymentService.createRazorPayOrder(String.valueOf(test.getAmount()));
			RazorPay razorPay = paymentService.getRazorPay((String)order.get("id"), test, user);

			return new ResponseEntity<String>(gson.toJson(paymentService.getResponse(razorPay, 200)),
					HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(gson.toJson(paymentService.getResponse(new RazorPay(), 500)),
				HttpStatus.BAD_GATEWAY);
	}
	
	@RequestMapping(value = "/test/{tid}/callback", method=RequestMethod.POST, consumes = {MediaType.ALL_VALUE})
	public String capturePayment(CallbackResponse response,Principal principal,@PathVariable int tid) {
		System.out.println("capturepayment method called");
		String paymentId = response.getRazorpay_payment_id();
	    String razorpaySignature = response.getRazorpay_signature();
	    String orderId = response.getRazorpay_order_id();
	    JSONObject options = new JSONObject();
	    if (StringUtils.isNotBlank(paymentId) && StringUtils.isNotBlank(razorpaySignature)
	        && StringUtils.isNotBlank(orderId)) {
	    	
	    	options.put("razorpay_payment_id", paymentId);
			options.put("razorpay_order_id", orderId);
			options.put("razorpay_signature", razorpaySignature);
			boolean isEqual = paymentService.check(options);
	
			if (isEqual) { 
				User user = userService.findUserByEmailid(principal.getName());
				Test test = testService.findById(tid);
				
				ParticipationPk pk = new ParticipationPk();
				pk.setTid(tid);
				pk.setUid(user.getUid());
				
				// getting total number of questions
				int totalQuestions = questionService.findQuestionsByTid(tid).size();
				// creating new participation
				Participation participation = new Participation();
				participation.setPk(pk);
				participation.setScore(0);
				participation.setLast_attempted(0);
				participation.setTotalQn(totalQuestions);
				participation.setPaymentDone(true);
				
				participationService.saveParticipation(participation);
				
				return "redirect:/user/dashboard";
			}
	    }
		return "redirect:/user/test/"+tid+"/payment?error";
	}

}
