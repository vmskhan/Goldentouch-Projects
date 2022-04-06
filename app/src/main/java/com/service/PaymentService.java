package com.service;

import org.json.JSONObject;

import com.entities.Test;
import com.entities.User;
import com.razorpay.Order;
import com.util.RazorPay;
import com.util.Response;

public interface PaymentService {
	Response getResponse(RazorPay razorPay, int statusCode);
	RazorPay getRazorPay(String orderId, Test test, User user);
	Order createRazorPayOrder(String amount);
	String convertRupeeToPaise(String paise);
	public Boolean check(JSONObject options);
}
